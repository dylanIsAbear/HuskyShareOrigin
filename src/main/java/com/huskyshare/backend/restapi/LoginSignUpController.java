package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.EmailHandler;
import com.huskyshare.backend.utils.JWTUtil;
import com.huskyshare.backend.utils.RedisHandler;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginSignUpController {
    /**
     * POST用户名与密码到/login进行登入，如果成功返回一个加密token，失败的话直接返回401错误。
     * 之后用户访问每一个需要权限的网址请求必须在header中添加Authorization字段，例如Authorization: token，token为密钥。
     * 后台会进行token的校验，如果有误会直接返回401
     */

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    RedisHandler redisHandler;

    @Autowired
    EmailHandler emailHandler;

    /**
     *
     * @param username
     * @param password
     * @return  401    Login fail
     *          201    Login Successfully ,  data   JWT Token
     */
    @RequestMapping(value = "/rest/v1.0/login", method = RequestMethod.POST)
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        logger.info("login INVOKE: username: " + username);
        User user = userService.findUserByUsername(username);
        if (user == null){
            user = userService.findUserByEmail(username);
            if(user==null) return new ResponseBean(401, "Login Fail", null);
        }

        if(user.getPassword().equals(password)){
            return new ResponseBean(201, "Success", JWTUtil.sign(username, password));
        }else{
            return new ResponseBean(401, "Login Fail", null);
        }
    }

    /**
     * Tester for authentication
     * @return
     */
    @GetMapping(value = "/rest/v1.0/testauth")
    @RequiresAuthentication
    public ResponseBean response(){
        return new ResponseBean(200, "You are authenticated", null);
    }

    /**
     * Tester for ROLE
     * @return
     */
    @GetMapping(value = "/rest/v1.0/testrole")
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    /**
     * 403 unauthorized error page
     * @return
     */
    @RequestMapping(path = "/error/403")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }

    /**
     * Store unconfirmed user and send code via e-mail
     * @param username
     * @param email
     * @param password
     * @param first
     * @param last
     * @return     402      Duplicate Email
     *             403      Duplicate Username
     *             201      Sign up successfully
     */
    @PostMapping(value = "/rest/v1.0/signup")
    public ResponseBean signup(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String first,
                               @RequestParam String last){
        if(userService.findUserByEmail(email)!=null){ return new ResponseBean(402, "Duplicate email", null); }
        if(userService.findUserByUsername(username)!=null){ return new ResponseBean(403, "Duplicate username", null); }
        User user = new User();
        user.setConfirmed(false);
        user.setEmail(email.toLowerCase());
        user.setPassword(password);
        user.setUsername(username);
        user.setRole("USER");
        user.setFirstName(first);
        user.setLastName(last);
        user.setPermission("post");
        userService.save(user);
        try {
            emailHandler.sendCode(email);
        }catch (Exception e){

        }
        return new ResponseBean(201, "Sign up successfully!", null);
    }

    @GetMapping(value = "/rest/v1.0/verify")
    public ResponseBean getCode(@RequestParam String email){
        User user = userService.findUserByEmail(email.toLowerCase());
        if(user != null && !user.isConfirmed()){

           try {
               emailHandler.sendCode(email);
               return new ResponseBean(201, "Send code successfully", null);
           }catch (Exception e){
               logger.error(e.getMessage());
               logger.error("Exception caused by: " + email);
               return new ResponseBean(401, "Error when disolving email: " + email, null);
           }
        }
        if(user == null) return new ResponseBean(401, "Null user with email: " + email, null);
        if(user.isConfirmed()) return new ResponseBean(401, "Duplicate confirmed user with email: " + email, null);
        return new ResponseBean(200, "", null);
    }

    /**
     * Verify email validated code
     * @param email
     * @param code
     * @return      201     Verify successfully
     *              401     Verify failed
     */
    @PostMapping(value = "/rest/v1.0/verify")
    public ResponseBean verifyCode(@RequestParam String email,
                                   @RequestParam String code){
        ResponseBean response;
        if(emailHandler.compareCode(email, code)){
            response = new ResponseBean(201, "Verify successfully!", null);
            User u = userService.findUserByEmail(email.toLowerCase());
            u.setConfirmed(true);
            userService.save(u);
        }else{ response = new ResponseBean(401, "Verify failed", null); }
        return response;
    }

}

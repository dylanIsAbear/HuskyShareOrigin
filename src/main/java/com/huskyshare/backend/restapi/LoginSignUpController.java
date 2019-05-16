package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.exception.UnauthorizedException;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @Autowired
    UserService userService;

    @PostMapping("/rest/v1.0/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        User user = userService.findUserByUsername(username);
        if (user == null) return new ResponseBean(401, "Login Fail", null);;

        if(user.getPassword().equals(password)){
            return new ResponseBean(201, "Success", JWTUtil.sign(username, password));
        }else{
            return new ResponseBean(401, "Login Fail", null);
        }
    }

    @GetMapping("/rest/v1.0/testauth")
    @RequiresAuthentication
    public ResponseBean response(){
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/rest/v1.0/testrole")
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @RequestMapping(path = "/error/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }

    @PostMapping("/rest/v1.0/signup")
    public ResponseBean signup(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String first,
                               @RequestParam String last){
        if(userService.findUserByEmail(email)!=null){ return new ResponseBean(402, "Duplicate email", null); }
        if(userService.findUserByUsername(username)!=null){ return new ResponseBean(403, "Duplicate username", null); }
        User user = new User();
        user.setConfirmed(false);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole("USER");
        user.setFirstName(first);
        user.setLastName(last);
        user.setPermission("post");
        userService.save(user);
        return new ResponseBean(201, "Sign up successfully!", null);
    }

}

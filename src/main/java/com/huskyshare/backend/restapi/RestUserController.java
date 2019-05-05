package com.huskyshare.backend.restapi;

import com.huskyshare.backend.form.ValidationForm;
import com.huskyshare.backend.json_entity.JSONUser;
import com.huskyshare.backend.json_entity.Jsonable;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.EmailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class RestUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailHandler emailHandler;

    /**
     * @status: 201 Success
     *          403 No Result
     *
     * @param uid
     * @return json response
     */
    @GetMapping(value = "/rest/user/get/{uid}")
    public Jsonable<JSONUser> searchUser(@PathVariable Long uid){
        Jsonable<JSONUser> response = new Jsonable<>();
        response.setDate(new Date());
        if(userService.findUserById(uid) == null){
            response.setDescription("No user found");
            response.setE(null);
            response.setMsg("false");
            response.setStatus("403");
        }else{
            response.setDescription("Success");
            JSONUser user = new JSONUser(userService.findUserById(uid));
            response.setE(user);
            response.setMsg("ok");
            response.setStatus("201");
        }
        return response;
    }

    @PostMapping(value = "/rest/user/validate")
    public Jsonable<Object> confirmUser(@RequestBody ValidationForm form){
        Jsonable<Object> response = new Jsonable<>();
        if(emailHandler.compareCode(form.getEmailAddress(), form.getCode())){
            response.setStatus("201");
            response.setMsg("ok");
            response.setE(null);
            response.setDate(new Date());
            response.setDescription("Confirm successfully");
        }else{
            response.setDescription("Validation code is incorrect!");
            response.setE(null);
            response.setMsg("Failed");
            response.setDate(new Date());
            response.setStatus("403");
        }

        return response;
    }


}

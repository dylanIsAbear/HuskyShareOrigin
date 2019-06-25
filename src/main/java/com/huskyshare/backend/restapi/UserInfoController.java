package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.json_entity.ResponseUserInfo;
import com.huskyshare.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/rest/v1.0/user/info")
    public ResponseBean getInfo(@RequestParam(required = false, defaultValue = "") String email,
                                   @RequestParam(required = false, defaultValue = "") String username){
        User user = email==null?userService.findUserByUsername(username):userService.findUserByEmail(email.toLowerCase());
        if(user == null) return new ResponseBean(200, "No user found with email: " + email + " or username: " + username, null);
        ResponseUserInfo userInfo = new ResponseUserInfo(user);
        return new ResponseBean(200, "Found info", userInfo);
    }
}

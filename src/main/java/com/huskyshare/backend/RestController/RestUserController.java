package com.huskyshare.backend.RestController;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rest/user/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public User searchUser(@PathVariable Long uid){
        return userService.findUserById(uid);
    }


}

package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.Tag;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {
    /**
     * This controller dissolves all Requests related to user profile and tags
     * HTTP Status:  201  Successful operation
     *               4xx  Illegal and failed operation redirect to /error?status={status}
     */
    @Autowired
    UserService userService;

    /**
     * Upload user's tags
     * @param tags User tags seperated by ',' i.e: tags=TA, TB, TC
     * @param Authorization Token
     * @return  201 if success
     */
    @PostMapping("/rest/v1.0/tags")
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean uploadTag(@RequestParam String tags,
                                  @RequestHeader String Authorization){
        String[] tagList = tags.split(",");
        User user = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        for(String tag : tagList){
            Tag t = new Tag();
            t.setUid(user.getId());
            t.setContent(tag);
            userService.saveTag(t);
        }
        return new ResponseBean(201, "Successfully upload tags", null);
    }

    /**
     * Return user's tag
     * @param Authorization login token
     * @return reponse with all user's tags
     */
    @GetMapping("/rest/v1.0/tags")
    public ResponseBean getTags(@RequestHeader String Authorization){
        return new ResponseBean(201, "Successfully get tags!",
                userService.findTags(userService.findUserByUsername(JWTUtil.getUsername(Authorization))
                        .getId()));
    }
}

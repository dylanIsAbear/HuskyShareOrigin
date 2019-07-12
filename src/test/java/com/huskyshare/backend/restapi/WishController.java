package com.huskyshare.backend.restapi;

import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Wish;
import com.huskyshare.backend.form.WishForm;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.json_entity.ResponseWish;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.service.WishService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WishController {
    @Autowired
    UserService userService;

    @Autowired
    WishService wishService;

    /**
     * POST wish
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/rest/v1.0/wish")
    @RequiresRoles("USER")
    @RequiresAuthentication
    public ResponseBean postWish(@RequestBody String content,
                                 @RequestBody String title,
                                 @RequestBody(required = false) String address,
                                 @RequestBody(required = false) String tags,
                                 @RequestHeader String Authorization){
        ResponseBean response = new ResponseBean();
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        response.setDate(new Date());
        Wish wish = new Wish();
        wish.setStatus(1); //ON
        wish.setDeleted(false);
        wish.setUser(current);
        wish.setWishContent(content);
        wish.setWishTitle(title);
        wish.setUid(current.getId());
        wishService.save(wish);
        response.setStatus(HttpStatus.SC_OK);
        response.setMsg("Post wish successfully!");
        return response;
    }

    /**
     * Need offset and limit
     * @param username
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles("USER")
    @RequestMapping(method = RequestMethod.GET, value = "/rest/v1.0/wish")
    public ResponseBean getAllWish(
            @RequestParam(required = false, defaultValue = "4") int limit,
            @RequestParam(required = false, defaultValue = "1") int offset,
            @RequestParam String username){
        ResponseBean response = new ResponseBean();
        response.setDate(new Date());
        List<Wish> wishList = wishService.findAllByUserAndLimit(userService.findUserByUsername(username), offset, limit);
        List<ResponseWish> responseWishList = new ArrayList<>();
        for (Wish w : wishList){
            responseWishList.add(new ResponseWish(w));
        }
        response.setStatus(200);
        response.setData(responseWishList);
        response.setMsg("Find wish successfully");
        return response;
    }
}

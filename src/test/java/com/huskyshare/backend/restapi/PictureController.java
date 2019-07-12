package com.huskyshare.backend.restapi;

import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.service.PictureService;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class PictureController {

    @Autowired
    UserService userService;
    @Autowired
    PictureService pictureService;

    /**
     * 获取用户头像
     * @param Authorization
     * @param request
     * @param response
     */
    @RequestMapping(value = "/rest/v1.0/picture", method = RequestMethod.GET)
    @RequiresAuthentication
    @RequiresRoles("USER")
    public void download(@RequestHeader String Authorization,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String path = userService.findProfile(userService.findUserByUsername(JWTUtil.getUsername(Authorization)).getProfile()).getPicture();
        File file = new File(path);

        if(!(file.exists() && file.canRead())) {
            file = new File("/Users/dylan/desktop/def.png");
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            inputStream.close();

            response.setContentType("image/png");

            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping(value = "/rest/v1.0/picture")
    public ResponseBean upload(@RequestBody MultipartFile picture,
                               @RequestHeader String Authorization){
        String url = "";
        try{
            url = pictureService.uploadOnePicture(picture, userService.findUserByUsername(JWTUtil.getUsername(Authorization)), 2);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(401, "Exception " + e.getMessage(), null);
        }
        return new ResponseBean(200, "Successful", url);
    }

}

package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.Profile;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.json_entity.ResponseBean;
import com.huskyshare.backend.json_entity.ResponseProfile;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.utils.EmailHandler;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class RestUserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailHandler emailHandler;

    @Value("${huskyshare.resource.profile.dir}")
    String uploadDir;
    //local directory to save pictures

    /**
     * @test NO TEST YET
     * @param file User's profile picture
     * @param description edited description
     * @param address location/school/dorm
     * @param Authorization login token
     * @return 201 if success,
     *         402 if picture's format is illegal
     *         401 if failed to upload picture
     */
    @RequestMapping(value = "/rest/v1.0/profile", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean upload(@RequestParam MultipartFile file,
                               @RequestParam String description,
                               @RequestParam String address,
                               @RequestHeader String Authorization){

        if (file.isEmpty()){ return new ResponseBean(401, "Empty picture", null); }

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String suffixName = fileName.split("\\.")[1];

        if(!(suffixName.contains("png") || suffixName.contains("jpg") || suffixName.contains("jpeg"))) return new ResponseBean(402, "Bad picture format", null);

        String fileDir = uploadDir;
        File pic = new File(fileDir + JWTUtil.getUsername(Authorization));
        System.out.println(fileDir + fileName);
        //if(!pic.getParentFile().exists()){pic.getParentFile().mkdirs();}

        try{
            file.transferTo(pic);

            Profile profile = new Profile();
            profile.setDescription(description);
            profile.setPicture(pic.getPath());
            profile.setAddress(address);

            User user = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
            profile.setUser_id(user.getId());
            userService.saveProfile(profile);

            user.setProfile(userService.findProfileByUid(user.getId()).getProfile_id());
            userService.save(user);

            return new ResponseBean(201, "Upload profile successfully", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseBean(401, "Upload profile failed", null);
    }

    @RequestMapping(value = "/rest/v1.0/profile", method = RequestMethod.GET)
    @RequiresAuthentication
    @RequiresRoles("USER")
    public ResponseBean getProfile(@RequestHeader String Authorization){
        Profile profile = userService.findProfile(
                userService.findUserByUsername(JWTUtil.getUsername(Authorization)).getId());
        ResponseProfile data = new ResponseProfile(profile);
        return new ResponseBean(201, "Successful", data);
    }

   /*@PostMapping(name = "/rest/v1.0/tag")
    public ResponseBean uploadTag(@RequestParam String tags,
                                  @RequestHeader String Authorization){
        User user = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        int times = 0;
        String[] tagList = tags.split(",");
        for(String tag : tagList){
            Tag t = new Tag();
            t.setContent(tag);
            t.setUid(user.getId());
            userService.saveTag(t);
            times++;
        }
        return new ResponseBean(201, "Successfully upload "  + " tags", null);
    }*/
}

package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.PictureDao;
import com.huskyshare.backend.entity.Picture;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.exception.FileExtensionException;
import com.huskyshare.backend.exception.IllegalExtensionException;
import com.huskyshare.backend.service.status.OssEnum;
import com.huskyshare.backend.utils.FileUtil;
import com.huskyshare.backend.utils.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PictureService {
    @Autowired
    PictureDao pictureDao;

    /**
     * Upload one single picture to Ali OSS server
     * @param file
     * @param user
     * @param type
     * @return identical MD5 secret
     * @throws Exception
     */
    public String uploadOnePicture(MultipartFile file,  User user, Integer type) throws Exception {
        /*String extension = FileUtil.getFileExtension(file.getOriginalFilename());
        if(extension == null) throw new FileExtensionException();
        if(!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")){
            throw new IllegalExtensionException();
        }*/
        if(file == null) throw new IOException();

        Picture picture = new Picture();
        picture.setType(type);

        if(type == 1){
            List<Picture> previous = pictureDao.findAllByType(1);
            for(Picture p : previous){
                p.setType(0);
                pictureDao.save(p);
            }
        }

        pictureDao.save(picture);
        if(file.getOriginalFilename()==null) throw new FileExtensionException();
        File newFile = new File(file.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(newFile);
        os.write(file.getBytes());
        os.close();
        file.transferTo(newFile);
        picture.setFilename(OSSUtil.upload(newFile));
        return picture.getFilename();
    }


}

package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.ProfileDao;
import com.huskyshare.backend.dao.TagDao;
import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.entity.Profile;
import com.huskyshare.backend.entity.Tag;
import com.huskyshare.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private TagDao tagDao;

    public String register(User user) {
        if (userDao.findByEmail(user.getEmail()) == null) {
            if (userDao.findByUsername(user.getUsername()) == null) {
                user.setConfirmed(false);
                userDao.save(user);
                return "SUCCESS";
            }
            return "DUPLICATE_USERNAME";
        }
        return "DUPLICATE_EMAIL";
    }

    public void save(User user){
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public String login(User user) {
        if (user.getEmail() != null) {
            User userInDB = userDao.findByEmail(user.getEmail());
            if(userInDB==null)
                return "ERROR_NO_USER";
            if(!userInDB.getPassword().equals(user.getPassword()))
                return "ERROR_INCORRECT_PASS";
            return "SUCCESS";
        }
        return "ERROR_INVALID";
    }

    public User findUserByUsername(String username){ return userDao.findByUsername(username);}

    public User findUserById(Long id){ return userDao.findById(id.intValue());}

    public User findUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public Profile saveProfile(Profile profile){ return profileDao.save(profile); }

    public Tag saveTag(Tag tag){
        return tagDao.save(tag);
    }

    public List<Tag> findTags(int uid){ return tagDao.findTagsByUid(uid); }

    public Profile findProfile(int id){return profileDao.findByProfile_id(id);}

    public Profile findProfileByUid(int id){return profileDao.findByUid(id);}

}


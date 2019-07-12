package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.ProfileDao;
import com.huskyshare.backend.dao.TagDao;
import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.entity.Profile;
import com.huskyshare.backend.entity.Tag;
import com.huskyshare.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private TagDao tagDao;


    public void save(User user){
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserByUsername(String username){ return userDao.findByUsername(username);}

    public User findUserById(Long id){ return userDao.findById(id).get();}

    public User findUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public Profile saveProfile(Profile profile){ return profileDao.save(profile); }

    public Tag saveTag(Tag tag){
        return tagDao.save(tag);
    }

    public List<Tag> findTags(Long uid){ return tagDao.findTagsByUid(uid); }

    public Profile findProfile(Long id){return profileDao.findByProfile_id(id);}

    public Profile findProfileByUid(Long id){return profileDao.findByUid(id);}

}


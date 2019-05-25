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

    @Transactional
    public void save(User user){
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserByUsername(String username){ return userDao.findByUsername(username);}

    public User findUserById(Long id){ return userDao.findById(id.intValue());}

    public User findUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Transactional
    public Profile saveProfile(Profile profile){ return profileDao.save(profile); }

    @Transactional
    public Tag saveTag(Tag tag){
        return tagDao.save(tag);
    }

    public List<Tag> findTags(int uid){ return tagDao.findTagsByUid(uid); }

    public Profile findProfile(int id){return profileDao.findByProfile_id(id);}

    public Profile findProfileByUid(int id){return profileDao.findByUid(id);}

}


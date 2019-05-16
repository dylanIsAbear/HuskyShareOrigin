package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.utils.EmailHandler;
import com.huskyshare.backend.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   @Autowired
   private UserDao userDao;

   @Autowired
   private EmailHandler emailHandler;

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

   public void confirm(String email){

   }

   public String registry(User user, String code){
      if(userDao.findByEmail(user.getEmail()) != null) return "DUPLICATE_EMAIL";
      if(userDao.findByUsername(user.getUsername()) != null) return "DUPLICATE_USERNAME";
      if(!emailHandler.compareCode(user.getEmail(), code)) return "WRONG_CODE";
      user.setConfirmed(true);
      userDao.save(user);
      return "SUCCESS";
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
}


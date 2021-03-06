package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.WishDao;
import com.huskyshare.backend.dao.WishReplyDao;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    @Autowired
    WishDao wishDao;

    @Autowired
    WishReplyDao wishReplyDao;

    public void save(Wish wish){
        wishDao.save(wish);
    }

    public Wish findById(Long id){
        return wishDao.findWishById(id);
    }

    public List<Wish>  findAllByUser (User user){
        return wishDao.findAllWishByUid(user.getId());
    }

    public List<Wish> findAllByUserAndLimit (User user, Integer offset, Integer limit){
        return wishDao.findAvailableWishByPageAndUid(user.getId(), offset, limit);
    }

    public List<Wish> findAllAvailableByUser(User user){
        return wishDao.findAllAvailableWish(user);
    }

}

package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.EmotionDao;
import com.huskyshare.backend.entity.EmotionReply;
import com.huskyshare.backend.entity.Emotion;
import com.huskyshare.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService {
    @Autowired
    EmotionDao emotionDao;

    /*
    @Autowired
    EmotionReply emotionReply;
    */

    public void save(Emotion emotion){
        emotionDao.save(emotion);
    }

    public Emotion findById(Long id){
        return emotionDao.findEmotionById(id);
    }

    public List<Emotion> findAllByUser (User user){
        return emotionDao.findAllEmotionByUser(user);
    }

    public List<Emotion> findAllAvailableByUser(User user){
        return emotionDao.findAllAvailableEmotion(user);
    }
}

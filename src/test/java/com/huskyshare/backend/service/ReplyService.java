package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.PostDao;
import com.huskyshare.backend.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    PostDao postDao;

    public void save(Post postReply){
        postDao.save(postReply);
    }

    public Post findReplyById(Long id) {
        return postDao.findPostById(id);
    }
}

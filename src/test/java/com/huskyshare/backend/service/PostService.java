package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.PostDao;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Post;
import com.huskyshare.backend.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao postDao;

    public void save(Post Post){
        postDao.save(Post);
    }

    public void deleteVote(Post post, Vote vote) {post.getVotes().remove(vote);}

    public Post findById(Long id){
        return postDao.findPostById(id);
    }

    public List<Post> findAllByUser (User user){
        return postDao.findAllPostByUser(user);
    }


}

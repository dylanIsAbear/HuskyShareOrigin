package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.dao.VoteDao;
import com.huskyshare.backend.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    private VoteDao voteDao;

    private UserDao userDao;

    public void save(Vote vote){
        voteDao.save(vote);
    }

    public void delete(Vote vote){voteDao.delete(vote);}

    public Vote findVoteByUser(Long id) {return voteDao.findVoteByUser(id);}
}

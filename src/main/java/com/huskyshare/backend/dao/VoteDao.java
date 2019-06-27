package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {
    @Query("select v from Vote v where v.userId = ?1")
    Vote findVoteByUser(Long userId);
}

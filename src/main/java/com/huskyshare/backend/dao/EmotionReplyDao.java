package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.EmotionReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionReplyDao extends JpaRepository<EmotionReply, Long> {
}

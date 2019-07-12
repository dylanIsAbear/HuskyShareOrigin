package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.EmotionReply;
import com.huskyshare.backend.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionReplyDao extends JpaRepository<EmotionReply, Long> {

}

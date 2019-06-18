package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionDao extends JpaRepository<Emotion, Long> {
}

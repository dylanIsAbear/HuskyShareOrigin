package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Emotion;
import com.huskyshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionDao extends JpaRepository<Emotion, Long> {
    @Query("select e from Emotion e where e.id = ?1")
    Emotion findEmotionById(Long id);

    @Query("select e from Emotion e where e.user = ?1")
    List<Emotion> findAllEmotionByUser(User user);

    @Query("select e from Emotion e where e.deleted = false AND e.user =?1")
    List<Emotion> findAllAvailableEmotion(User user);
}

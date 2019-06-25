package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.EmotionReply;
import com.huskyshare.backend.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionReplyDao extends JpaRepository<EmotionReply, Long> {
    @Query("select r from EmotionReply r where r.Emotion = ?1 and r.isParentReply = true order by r.vote")
    List<EmotionReply> findAllParentReplySortedByVoteUp(Emotion Emotion);

    @Query("select r from EmotionReply r where r.Emotion = ?1 and r.isParentReply = true order by r.createdTime")
    List<EmotionReply> findAllParentReplySortedByCreatedTime(Emotion Emotion);

    @Query("select r from EmotionReply r where r.parent = ?1 order by r.createdTime")
    List<EmotionReply> findAllChildrenReplySortedByCreatedTime(EmotionReply reply);

    @Query("select r from EmotionReply r where r.to = ?1 order by r.createdTime")
    List<EmotionReply> findAllParentReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from EmotionReply r where r.to = ?1 AND r.isParentReply = false order by r.createdTime")
    List<EmotionReply> findAllChildrenReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from EmotionReply r where r.Emotion = ?1 and r.isParentReply = true and r.deleted = false order by r.vote")
    List<EmotionReply> findAllAvailableParentReplySortedByCreatedTime(Emotion Emotion);

    @Query("select r from EmotionReply r where r.Emotion = ?1 and r.isParentReply = true and r.deleted = false  order by r.vote")
    List<EmotionReply> findAllAvailableParentReplySortedByVoteUp(Emotion Emotion);

    @Query("select r from EmotionReply r where r.parent = ?1 and r.deleted = false order by r.createdTime")
    List<EmotionReply> findAllAvailableChildrenReplySortedByCreatedTime(EmotionReply reply);

    @Query("select r from EmotionReply r where r.to = ?1 and r.deleted = false  order by r.createdTime")
    List<EmotionReply> findAllAvailableParentReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from EmotionReply r where r.to = ?1 AND r.isParentReply = false and r.deleted = false  order by r.createdTime")
    List<EmotionReply> findAllAvailableChildrenReplyAtUserSortedByCreatedTime(Long uid);
}

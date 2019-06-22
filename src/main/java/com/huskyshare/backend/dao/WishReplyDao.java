package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Wish;
import com.huskyshare.backend.entity.WishReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishReplyDao extends JpaRepository<WishReply, Long> {
    @Query("select r from WishReply r where r.wish = ?1 and r.isParentReply = true order by r.vote")
    List<WishReply> findAllParentReplySortedByVoteUp(Wish wish);

    @Query("select r from WishReply r where r.wish = ?1 and r.isParentReply = true order by r.createdTime")
    List<WishReply> findAllParentReplySortedByCreatedTime(Wish wish);

    @Query("select r from WishReply r where r.parent = ?1 order by r.createdTime")
    List<WishReply> findAllChildrenReplySortedByCreatedTime(WishReply reply);

    @Query("select r from WishReply r where r.to = ?1 order by r.createdTime")
    List<WishReply> findAllParentReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from WishReply r where r.to = ?1 AND r.isParentReply = false order by r.createdTime")
    List<WishReply> findAllChildrenReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from WishReply r where r.wish = ?1 and r.isParentReply = true and r.deleted = false order by r.vote")
    List<WishReply> findAllAvailableParentReplySortedByCreatedTime(Wish wish);

    @Query("select r from WishReply r where r.wish = ?1 and r.isParentReply = true and r.deleted = false  order by r.vote")
    List<WishReply> findAllAvailableParentReplySortedByVoteUp(Wish wish);

    @Query("select r from WishReply r where r.parent = ?1 and r.deleted = false order by r.createdTime")
    List<WishReply> findAllAvailableChildrenReplySortedByCreatedTime(WishReply reply);

    @Query("select r from WishReply r where r.to = ?1 and r.deleted = false  order by r.createdTime")
    List<WishReply> findAllAvailableParentReplyAtUserSortedByCreatedTime(Long uid);

    @Query("select r from WishReply r where r.to = ?1 AND r.isParentReply = false and r.deleted = false  order by r.createdTime")
    List<WishReply> findAllAvailableChildrenReplyAtUserSortedByCreatedTime(Long uid);

}

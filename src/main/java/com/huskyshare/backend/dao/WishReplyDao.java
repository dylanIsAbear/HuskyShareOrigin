package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.WishReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishReplyDao extends JpaRepository<WishReply, Long> {
}

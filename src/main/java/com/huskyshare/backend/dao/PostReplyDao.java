package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReplyDao extends JpaRepository<PostReply, Long> {
}

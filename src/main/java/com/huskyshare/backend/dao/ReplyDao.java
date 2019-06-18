package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyDao extends JpaRepository<Reply, Long> {
}

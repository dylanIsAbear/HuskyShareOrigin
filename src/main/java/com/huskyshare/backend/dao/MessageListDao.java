package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.MessageList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageListDao extends JpaRepository<MessageList, Long> {
}

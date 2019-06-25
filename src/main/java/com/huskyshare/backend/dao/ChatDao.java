package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Chat;
import com.huskyshare.backend.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDao extends JpaRepository<Chat, Long> {
    @Query("select c from Chat c where c.chatHistory = ?1")
    List<Chat> findAllByChatHistory(ChatHistory chatHistory);
}

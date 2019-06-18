package com.huskyshare.backend.dao;
import com.huskyshare.backend.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatHistoryDao extends JpaRepository<ChatHistory, Long> {
}

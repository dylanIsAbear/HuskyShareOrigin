package com.huskyshare.backend.dao.ChatRepository;

import com.huskyshare.backend.entity.chat.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface MessageDao extends CassandraRepository<Message, Long> {
    List<Message> findMessagesByChatRoomId(Long chatRoomId);
}

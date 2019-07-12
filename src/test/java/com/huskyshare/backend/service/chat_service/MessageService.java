package com.huskyshare.backend.service.chat_service;

import com.huskyshare.backend.dao.ChatRepository.MessageDao;
import com.huskyshare.backend.entity.chat.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private PrivateChatRoomService chatRoomService;


    // now only work for private message
    public void saveMessage(Message message) {
        message.setUsername(message.getSendUser());
        messageDao.save(message);

        message.setUsername(message.getReceiveUser());
        messageDao.save(message);
    }


    public List<Message> findAllMessagesFor(Long chatRoomId) {
        return messageDao.findMessagesByChatRoomId(chatRoomId);
    }


}

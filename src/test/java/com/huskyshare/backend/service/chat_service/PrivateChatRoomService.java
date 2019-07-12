package com.huskyshare.backend.service.chat_service;

import com.huskyshare.backend.dao.ChatRepository.PrivateChatRoomDao;
import com.huskyshare.backend.entity.chat.Message;
import com.huskyshare.backend.entity.chat.PrivateChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrivateChatRoomService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private PrivateChatRoomDao privateChatRoomDao;

    @Autowired
    private MessageService messageService;


    public void sendPrivateMessage(Message message) {
        webSocketMessagingTemplate.convertAndSendToUser(
                message.getReceiveUser(),
                "/queue/" + message.getChatRoomId() + ".private.messages",
                message);

        webSocketMessagingTemplate.convertAndSendToUser(
                message.getSendUser(),
                "/queue/" + message.getChatRoomId() + ".private.messages",
                message);

        messageService.saveMessage(message);
    }

    public PrivateChatRoom findPrivateChatRoomByUsers(Long user1Id, Long user2Id) {
        return privateChatRoomDao.findPrivateChatRoomByUsers(user1Id, user2Id);
    }

    public void save(PrivateChatRoom chatRoom) {
        privateChatRoomDao.save(chatRoom);
    }
}

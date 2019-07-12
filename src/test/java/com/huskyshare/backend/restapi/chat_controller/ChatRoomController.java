package com.huskyshare.backend.restapi.chat_controller;

import com.huskyshare.backend.entity.chat.Message;
import com.huskyshare.backend.entity.chat.PrivateChatRoom;
import com.huskyshare.backend.service.chat_service.MessageService;
import com.huskyshare.backend.service.chat_service.PrivateChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;

import java.security.Principal;
import java.util.List;

public class ChatRoomController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PrivateChatRoomService privateChatRoomService;

    public void createPrivateChatRoom(Long user1Id, Long user2Id) {
        PrivateChatRoom chatRoom = new PrivateChatRoom(user1Id, user2Id);
        chatRoom.setUser1Id(user1Id);
        chatRoom.setUser2Id(user2Id);
        privateChatRoomService.save(chatRoom);
    }

    @SubscribeMapping("/old.messages")
    public List<Message> listOldMessagesFromUserOnSubscribe(SimpMessageHeaderAccessor headerAccessor) {
        Long chatRoomId = (Long) headerAccessor.getSessionAttributes().get("chatRoomId");
        return messageService.findAllMessagesFor(chatRoomId);
    }

    @MessageMapping("/send.message")
    public void sendMessage(@Payload Message message, Principal principal,
                            SimpMessageHeaderAccessor headerAccessor) {
        Long chatRoomId = (Long) headerAccessor.getSessionAttributes().get("chatRoomId");
        message.setSendUser(principal.getName());
        message.setChatRoomId(chatRoomId);
        privateChatRoomService.sendPrivateMessage(message);
    }
}

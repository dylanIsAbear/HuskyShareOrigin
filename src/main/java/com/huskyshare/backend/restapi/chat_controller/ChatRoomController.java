package com.huskyshare.backend.restapi.chat_controller;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.chat.Message;
import com.huskyshare.backend.entity.chat.PrivateChatRoom;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.service.chat_service.MessageService;
import com.huskyshare.backend.service.chat_service.PrivateChatRoomService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ChatRoomController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PrivateChatRoomService privateChatRoomService;

    @Autowired
    private UserService userService;

    public void createPrivateChatRoom(Long user1Id, Long user2Id) {
        PrivateChatRoom chatRoom = new PrivateChatRoom(user1Id, user2Id);
        chatRoom.setUser1Id(user1Id);
        chatRoom.setUser2Id(user2Id);
        privateChatRoomService.save(chatRoom);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/v1.0/get_private_chatroom")
    @RequiresAuthentication
    public Long getPrivateChatroom(@RequestHeader String Authorization, Long user2Id) {
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        return privateChatRoomService.findPrivateChatRoomByUsers(current.getId(), user2Id).getId();
    }

    @SubscribeMapping("/old.messages")
    public List<Message> listOldMessagesFromUserOnSubscribe(Long chatRoomId) {
        return messageService.findAllMessagesFor(chatRoomId);
    }

    @MessageMapping("/send.message")
    @RequiresAuthentication
    public void sendMessage(@Payload Message message, @RequestHeader String Authorization, Long chatRoomId) {
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        message.setSendUser(current.getUsername());
        // the receive user is set previously in frontend code
        message.setChatRoomId(chatRoomId);
        privateChatRoomService.sendPrivateMessage(message);
    }
}

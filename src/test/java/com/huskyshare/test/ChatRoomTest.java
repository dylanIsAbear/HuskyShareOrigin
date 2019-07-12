package com.huskyshare.test;

import com.huskyshare.backend.dao.ChatRepository.MessageDao;
import com.huskyshare.backend.dao.ChatRepository.PrivateChatRoomDao;
import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.chat.PrivateChatRoom;
import com.huskyshare.backend.service.chat_service.PrivateChatRoomService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatRoomTest {
    @Autowired
    private PrivateChatRoomService privateChatRoomService;
    @Autowired
    private PrivateChatRoomDao privateChatRoomDao;
    @Autowired
    private MessageDao messageDao;

    private PrivateChatRoom chatRoom;

    @Test
    public void shouldJoinUsersToChatRoom() {
        User user1 = new User();
        user1.setId(Long.valueOf(123));
        User user2 = new User();
        user2.setId(Long.valueOf(345));
        chatRoom = new PrivateChatRoom(user1.getId(), user2.getId());
        chatRoom.setId(Long.valueOf(111));
    }

    @After
    public void destroy() {
        privateChatRoomDao.delete(chatRoom);
        messageDao.deleteAll();
    }

}

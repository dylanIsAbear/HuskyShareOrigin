package com.huskyshare.backend.dao.ChatRepository;

import com.huskyshare.backend.entity.chat.PrivateChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChatRoomDao extends JpaRepository<PrivateChatRoom, Long> {
    @Query("select c from PrivateChatRoom c where (c.user1Id = ?1 AND c.user2Id = ?2) OR (c.user1Id = ?2 AND c.user2Id = ?1)")
    PrivateChatRoom findPrivateChatRoomByUsers(Long user1Id, Long user2Id);

    @Query("select c from PrivateChatRoom c where c.id = ?1")
    PrivateChatRoom findPrivateChatRoomById(Long id);
}

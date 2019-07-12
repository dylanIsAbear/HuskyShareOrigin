package com.huskyshare.backend.entity.chat;

import javax.persistence.*;

@Entity
@Table(name = "chat_rooms")
public class PrivateChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user1")
    private Long user1Id;

    @Column(name="user2")
    private Long user2Id;

    public PrivateChatRoom(long user1Id, long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateChatRoom)) return false;

        PrivateChatRoom chatRoom = (PrivateChatRoom) o;

        if (getId() != null ? !getId().equals(chatRoom.getId()) : chatRoom.getId() != null) return false;
        if (getUser1Id() != null ? !getUser1Id().equals(chatRoom.getUser1Id()) : chatRoom.getUser1Id() != null)
            return false;
        return getUser2Id() != null ? getUser2Id().equals(chatRoom.getUser2Id()) : chatRoom.getUser2Id() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser1Id() != null ? getUser1Id().hashCode() : 0);
        result = 31 * result + (getUser2Id() != null ? getUser2Id().hashCode() : 0);
        return result;
    }
}

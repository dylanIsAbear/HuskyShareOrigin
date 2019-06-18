package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestDao extends JpaRepository<FriendRequest, Long> {
}

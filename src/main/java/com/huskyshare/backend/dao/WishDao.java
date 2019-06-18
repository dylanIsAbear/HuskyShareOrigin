package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishDao extends JpaRepository<Wish, Long> {
}

package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewDao extends JpaRepository<View, Long> {
}

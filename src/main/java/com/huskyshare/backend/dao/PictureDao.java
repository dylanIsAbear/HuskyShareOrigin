package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDao extends JpaRepository<Picture, Long> {
}

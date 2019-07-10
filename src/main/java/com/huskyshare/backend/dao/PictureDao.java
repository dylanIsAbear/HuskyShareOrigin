package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureDao extends JpaRepository<Picture, Long> {
    @Query("select p from Picture p where p.type = ?1")
    List<Picture> findAllByType(Integer type);
}

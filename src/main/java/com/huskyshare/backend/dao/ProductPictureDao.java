package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.ProductPictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPictureDao extends JpaRepository<ProductPictures, Long> {
    @Query("select p from ProductPictures p where p.pid = ?1")
    List<ProductPictures> findByProductId(Integer id);
}

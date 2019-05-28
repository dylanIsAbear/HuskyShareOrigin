package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.sellerId = ?1")
    List<Product> findByUid(Integer uid);

    @Query("select p from Product p where p.consumerId = ?1")
    List<Product> findByBuyerId(Integer uid);

    @Query("select p from Product p where p.title like %?1%")
    List<Product> searchByTitle(String key);

    @Query("select p from Product p where p.childTitle like %?1%")
    List<Product> searchByChildTitle(String key);

    @Query("select p from Product p where p.description like %?1%")
    List<Product> searchByDescription(String key);
}


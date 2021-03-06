package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesDao extends JpaRepository<Favorites, Long> {
}

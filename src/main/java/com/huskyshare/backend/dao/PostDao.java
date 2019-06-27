package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Post;
import com.huskyshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.id = ?1")
    Post findPostById(Long id);

    @Query("select p from Post p where p.user = ?1")
    List<Post> findAllPostByUser(User user);

}

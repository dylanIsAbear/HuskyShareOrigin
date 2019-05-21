package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagDao extends JpaRepository<Tag, Long> {
    @Query("select t from Tag t where t.uid is ?1")
    List<Tag> findTagsByUid(int uid);
}

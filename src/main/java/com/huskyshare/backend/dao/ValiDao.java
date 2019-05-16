package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ValiDao extends JpaRepository<Code, Long> {
    @Query("select code from Code code where code.email=?1")
    Code findCode(String email);
}

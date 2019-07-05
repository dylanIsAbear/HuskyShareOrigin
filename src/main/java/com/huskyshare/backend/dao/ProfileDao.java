package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Long> {
    @Query("select p from Profile p where p.profile_id = ?1")
    Profile findByProfile_id(Long id);

    @Query("select p from Profile p where p.user_id = ?1")
    Profile findByUid(Long id);
}

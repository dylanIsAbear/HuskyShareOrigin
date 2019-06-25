package com.huskyshare.backend.dao;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishDao extends JpaRepository<Wish, Long> {
    @Query("select w from Wish w where w.id = ?1")
    Wish findWishById(Long id);

    @Query("select w from Wish w where w.user = ?1")
    List<Wish> findAllWishByUser(User user);

    @Query("select w from Wish w where w.uid = ?1")
    List<Wish> findAllWishByUid(Integer uid);

    @Query("select w from Wish w where w.deleted = false AND w.user =?1")
    List<Wish> findAllAvailableWish(User user);

    @Query(nativeQuery = true, value = "select * from wish where uid = ?1 limit ?2 , ?3")
    List<Wish> findAvailableWishByPageAndUid(Integer uid, Integer offset, Integer limit);

    @Query("select w from Wish w where w.deleted = true AND w.user=?1")
    List<Wish> findAllDeletedWish(User user);

    @Query("select w from Wish w where w.status = 0 AND w.user=?1")
    List<Wish> findAllFinishedWish(User user);

    @Query("select w from Wish w where w.status = 1 AND w.user=?1")
    List<Wish> findAllCurrentWish(User user);
}

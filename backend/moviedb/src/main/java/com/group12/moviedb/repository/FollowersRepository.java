package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Integer> {
    Followers findByUserId(Integer userId);
    List<Followers> findByFollowing(boolean following);
    Followers findByUserIdAndFollowing(Integer userId, boolean following);
    @SuppressWarnings("null")
    Optional<Followers> findById(Integer id);
    void deleteByUserId(Integer user_id);
    void deleteByUserIdAndFollowing(Integer user_id, boolean following);
}

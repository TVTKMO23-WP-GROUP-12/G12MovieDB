package com.group12.moviedb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Integer> {
    Followers findByUserId(int userId);
    List<Followers> findByFollowing(boolean following);
    Followers findByUserIdAndFollowing(int userId, boolean following);
    Followers deleteByUserId(int userId);
    Followers deleteByUserIdAndFollowing(int userId, boolean following);
}

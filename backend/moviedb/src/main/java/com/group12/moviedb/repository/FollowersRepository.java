package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Integer> {
    Followers findByUserId(Integer id);
    List<Followers> findByFollowing(boolean following);
    Followers findByUserIdAndFollowing(Integer userId, boolean following);
    Optional<Followers> findById(Integer follwerId);
    void deleteByUserId(Integer id);
    void deleteByUserIdAndFollowing(Integer userId,boolean following);
}

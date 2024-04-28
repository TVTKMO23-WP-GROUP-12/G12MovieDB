package com.group12.moviedb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByGroupName(String groupName);
    Optional<Group> findById(int id);
}

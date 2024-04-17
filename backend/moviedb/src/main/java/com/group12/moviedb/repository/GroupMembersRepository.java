package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.User;
import com.group12.moviedb.models.GroupMembers;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, Integer> {
    List<GroupMembers> findByGroup(Group group);
    List<GroupMembers> findByUser(User user);
    Optional<GroupMembers> findById(Integer member_id);
    void deleteById(int member_id);
    GroupMembers save(Group groupMembers);
}

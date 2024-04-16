package com.group12.moviedb.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Followers;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.FollowersRepository;
import com.group12.moviedb.repository.UserRepository;

@RestController
public class FollowersController {
    
    private final FollowersRepository followersRepository;
    private final UserRepository userRepository;

    public FollowersController(FollowersRepository followersRepository, UserRepository userRepository) {
        this.followersRepository = followersRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/followers")
    public List<Followers> getFollowers() {
        return followersRepository.findAll();
    }

    @GetMapping("/followers/user={user_id}")
    public Followers getFollowersById(@PathVariable int userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return followersRepository.findByUserId(userId);
    }

    @GetMapping("/followers/following={following}")
    public List<Followers> getFollowersByFollowing(@PathVariable boolean following) {
        return followersRepository.findByFollowing(following);
    }

    @GetMapping("/followers/user={user_id}&following={following}")
    public Followers getFollowersByUserIdAndFollowing(@PathVariable int userId, @PathVariable boolean following) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return followersRepository.findByUserIdAndFollowing(userId, following);
    }

    @PostMapping("/followers")
    public Followers addFollowers(@RequestBody Followers followers) {
        return followersRepository.save(followers);
    }

    @PostMapping("/followers/")
    public Followers addFollowersByUserId(  @RequestBody Followers followers,
                                            @RequestParam("userId") int userId,
                                            @RequestParam("followerId") int followerId){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        User follower = userRepository.findById(followerId)
            .orElseThrow(() -> new NoSuchElementException("Follower not found"));
        followers.setFollowerId(follower.getId());
        followers.setFollowing(true);
        followers.setJoinedAt(LocalDateTime.now());
        followers.setLeftAt(null);
        followers.setUser(user);
        return followersRepository.save(followers);
    }

    @PostMapping("/followers/user={user_id}&following={following}")
    public Followers addFollowersByUserIdAndFollowing(@PathVariable int userId, @PathVariable boolean following, @RequestBody Followers followers) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        followers.setUser(user);
        followers.setFollowing(following);
        return followersRepository.save(followers);
    }

    @PatchMapping("/followers/{id}")
    public Followers updateFollowers(@PathVariable int id, @RequestBody Followers followers) {
        Followers followersToUpdate = followersRepository.findById(id).orElse(null);
        if (followersToUpdate == null) {
            return null;
        }
        followersToUpdate.setFollowing(followers.isFollowing());
        followersToUpdate.setLeftAt(followers.getLeftAt());
        followersToUpdate.setJoinedAt(followers.getJoinedAt());
        return followersRepository.save(followersToUpdate);
    }

    @PatchMapping("/followers/{id}&following={following}")
    public Followers updateFollowersFollowing(@PathVariable int id, @PathVariable boolean following) {
        Followers followersToUpdate = followersRepository.findById(id).orElse(null);
        if (followersToUpdate == null) {
            return null;
        }
        followersToUpdate.setFollowing(following);
        return followersRepository.save(followersToUpdate);
    }

    @DeleteMapping("/followers/{id}")
    public void deleteFollowers(@PathVariable int id) {
        followersRepository.deleteById(id);
    }

    @DeleteMapping("/followers/user={user_id}")
    public void deleteFollowersByUserId(@PathVariable int userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        followersRepository.deleteByUserId(userId);
    }

    @DeleteMapping("/followers/user={user_id}&following={following}")
    public void deleteFollowersByUserIdAndFollowing(@PathVariable int userId, @PathVariable boolean following) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        followersRepository.deleteByUserIdAndFollowing(userId, following);
    }

}

package com.bucketdev.betapp.oauth.svc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bucketdev.betapp.oauth.svc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);
    Set<User> findByDisplayNameContaining(String name);
    User findByUsername(String username);
}
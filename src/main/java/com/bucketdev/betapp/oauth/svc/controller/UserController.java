package com.bucketdev.betapp.oauth.svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bucketdev.betapp.oauth.svc.model.User;
import com.bucketdev.betapp.oauth.svc.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping("/user/{username}")
	public User findUser(@PathVariable String username) {
		return repo.findByUsername(username);
	}
}

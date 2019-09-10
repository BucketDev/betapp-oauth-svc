package com.bucketdev.betapp.oauth.svc.config;

import java.util.Set;

import com.bucketdev.betapp.oauth.svc.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO dto);
    UserDTO findByUid(String uid);
    Set<UserDTO> findByDisplayName(String uid);
    UserDTO findByUsername(String username);

}

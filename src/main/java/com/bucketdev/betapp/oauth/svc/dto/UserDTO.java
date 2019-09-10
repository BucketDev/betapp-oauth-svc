package com.bucketdev.betapp.oauth.svc.dto;

import java.io.Serializable;
import java.util.List;

import com.bucketdev.betapp.oauth.svc.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 4062907351400027403L;
	
	private long id;
    private String uid;
    private String email;
    private String displayname;
    private String photoUrl;
    private String provider;
    private String username;
    private String password;
    private int attempts;
    private boolean enabled;
    private List<Role> roles;

}

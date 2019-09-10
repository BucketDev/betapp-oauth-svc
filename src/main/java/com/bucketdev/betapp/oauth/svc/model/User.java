package com.bucketdev.betapp.oauth.svc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.bucketdev.betapp.oauth.svc.dto.UserDTO;
import com.bucketdev.betapp.oauth.svc.model.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String uid;
    
    @Column
    @NotNull
    private String username;
    
    @Column
    @NotNull
    private String password;
    
    @Column
    private boolean enabled;

    @Column
    @NotNull
    private String email;

    @Column
    private String displayName;

    @Column
    private String photoUrl;

    @Column
    @NotNull
    private String provider;
    
    @Column
    private int attemps;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = { "user_id", "role_id" }) })
	private List<Role> roles;
    
    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();

        dto.setId(id);
        dto.setUid(uid);
        dto.setEmail(email);
        dto.setDisplayname(displayName);
        dto.setPhotoUrl(photoUrl);
        dto.setProvider(provider);
        dto.setAttempts(attemps);
        dto.setUsername(username);
        
        return dto;
    }

    public void setValuesFromDTO(UserDTO dto) {
        uid = dto.getUid();
        email = dto.getEmail();
        displayName = dto.getDisplayname();
        photoUrl = dto.getPhotoUrl();
        provider = dto.getProvider();
    }

}

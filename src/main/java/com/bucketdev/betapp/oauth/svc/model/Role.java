package com.bucketdev.betapp.oauth.svc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bucketdev.betapp.oauth.svc.model.Role;

import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 30)
	private String nombre;
}


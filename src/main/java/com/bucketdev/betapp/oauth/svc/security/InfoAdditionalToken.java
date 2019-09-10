package com.bucketdev.betapp.oauth.svc.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.bucketdev.betapp.oauth.svc.config.UserService;
import com.bucketdev.betapp.oauth.svc.dto.UserDTO;

@Component
public class InfoAdditionalToken implements TokenEnhancer{
	
	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		UserDTO usuario = userService.findByUsername(authentication.getName());
		
		info.put("uid", usuario.getUid());
		info.put("nombre", usuario.getUsername());
		info.put("email", usuario.getEmail());
		info.put("photo", usuario.getPhotoUrl());
		info.put("enabled", usuario.isEnabled());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
}
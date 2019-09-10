package com.bucketdev.betapp.oauth.svc.security.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.bucketdev.betapp.oauth.svc.dto.UserDTO;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{
	
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private com.bucketdev.betapp.oauth.svc.config.UserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		String message = "Success login " + authentication.getName();
		log.info(message);
		
		UserDTO usuario = userService.findByUsername(authentication.getName());
		
		if(usuario.getAttempts() != -1 && usuario.getAttempts()>0) {
			usuario.setAttempts(0);
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error en el login " + exception.getMessage();
		log.info(message);
		
		try {
			log.info(authentication.getName());
			UserDTO usuario = userService.findByUsername(authentication.getName());
			log.info(usuario.toString());
		} catch (Exception e) {
			log.info(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}
		
	}

}


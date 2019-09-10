package com.bucketdev.betapp.oauth.svc.config;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bucketdev.betapp.oauth.svc.dto.UserDTO;
import com.bucketdev.betapp.oauth.svc.exception.user.UserNotFoundException;
import com.bucketdev.betapp.oauth.svc.model.User;
import com.bucketdev.betapp.oauth.svc.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDTO save(UserDTO dto) {
		User user = new User();
        if(dto.getId() > 0) {
            Optional<User> userOptional = userRepository.findById(dto.getId());
            if(!userOptional.isPresent())
                throw new UserNotFoundException("id: " + dto.getId());
            user = userOptional.get();
        }
        user.setValuesFromDTO(dto);
        return userRepository.save(user).toDTO();
	}

	@Override
	public UserDTO findByUid(String uid) {
		return userRepository.findByUid(uid).toDTO();
	}

	@Override
	public Set<UserDTO> findByDisplayName(String name) {
		return userRepository.findByDisplayNameContaining(name).stream().map(User::toDTO).collect(Collectors.toSet());
	}

	@Override
	public UserDTO findByUsername(String username) {
		log.info(userRepository.findByUsername(username).toDTO().toString());
		return userRepository.findByUsername(username).toDTO();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User usuario = userRepository.findByUsername(username);
			log.info(usuario.toString());

			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

			return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
					authorities);
			
		} catch (Exception e) {
			log.error("Error en el login, no existe el usuario en el sistema '"+ username +"' ");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario en el sistema '" + username + "' ");
		}
	}

}

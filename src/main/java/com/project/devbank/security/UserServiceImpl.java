package com.project.devbank.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.devbank.model.Client;
import com.project.devbank.repositories.ClientRepository;
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String cpf) {
      Optional<Client> userEntity = clientRepository.findByCpf(cpf);
      if (userEntity.get() == null) {
          throw new UsernameNotFoundException(cpf);
      }
      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
      userEntity.get().getRoles().forEach(role -> {
          authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
      });
      UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.get().getCpf(),
              userEntity.get().getPassword(),
              authorities);
      return user;
  }

}






package com.product_list.service;

import com.product_list.model.User;
import com.product_list.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Tentando autenticar usuário: {}", username);

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> {
                log.warn("Usuário '{}' não encontrado.", username);
                return new UsernameNotFoundException("User not found");
            });

        List<SimpleGrantedAuthority> authorities;

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            log.warn("Usuário '{}' não possui roles definidas. Usando lista vazia.", username);
            authorities = Collections.emptyList();
        } else {
            authorities = user.getRoles().stream()
                .filter(role -> role != null && !role.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        }

        log.info("Usuário '{}' autenticado com roles: {}", username, authorities);
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", user.getPassword());

        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            password = "{noop}";
        }

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            password,
            authorities
        );
    }
}

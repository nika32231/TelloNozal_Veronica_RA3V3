package com.dam.accesodatos.tellonozal_veronica_ra3v3.security;

import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Usuario;
import com.dam.accesodatos.tellonozal_veronica_ra3v3.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + u.getRol().name())
        );

        return User.builder()
                .username(u.getUsername())
                .password(u.getPasswordHash())
                .authorities(authorities)
                .accountLocked(Boolean.FALSE.equals(u.getActivo()))
                .disabled(Boolean.FALSE.equals(u.getActivo()))
                .build();
    }
}

package com.example.tienda.service;

import com.example.tienda.entity.Usuario;

import com.example.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailService implements UserDetailsService {

    @Autowired
    private  UsuarioRepository usuarioRepository;





    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca al usuario por su correo electrónico
        Usuario user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }


        // Construye un objeto UserDetails de Spring Security utilizando los datos del usuario
        return User.builder()
                .username(user.getEmail()) // Utiliza el correo electrónico como nombre de usuario
                .password(user.getPassword())
                .roles(user.getRol().name()) // Asigna el rol del usuario como rol en Spring Security
                .build();
    }

}


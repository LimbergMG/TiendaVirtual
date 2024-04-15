package com.example.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tienda.entity.Rol;
import com.example.tienda.entity.Usuario;
import com.example.tienda.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
    public void registrar(Usuario user) {
    	user.setRol(Rol.USER); // Asigna el rol al usuario
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codifica la contraseña
        user.setNombre(user.getNombre());
		user.setApellido(user.getApellido());
        
        usuarioRepository.save(user); // Guarda el usuario en la base de datos
    }
	
	 public Usuario findByEmail(String email) {
	        return usuarioRepository.findByEmail(email);
	    }


}

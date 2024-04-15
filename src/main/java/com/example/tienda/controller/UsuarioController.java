package com.example.tienda.controller;

import com.example.tienda.entity.producto.Ropa;
import com.example.tienda.service.RopaService;
import com.example.tienda.service.RopaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tienda.entity.Usuario;
import com.example.tienda.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private RopaServiceImpl ropaService;
	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;

	@GetMapping("/login")
	public String mostrarFormularioLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/login";
	}


	@GetMapping("/registracion")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("user", new Usuario());
		return "registracion";
	}

	@PostMapping("/registracion")
	public String registerUser(@ModelAttribute Usuario usuario, Model model) {
		usuarioService.registrar(usuario);

		return  "redirect:/login";
	}
	@GetMapping("/add")
	public String mostrarFormularioAgregar(Model model) {
		return "add";
	}

	@PostMapping("/add")
	public String addRopa(@ModelAttribute Ropa ropa, @RequestParam("imagen") MultipartFile imagen, Model model) {
		try {
			String rutaImagen = guardarImagen(imagen);
			ropa.setImagene(rutaImagen);

			Ropa ropaGuardada = ropaService.guardarRopa(ropa);

			System.out.println("Producto agregado: " + ropaGuardada);

			List<Ropa> listaRopas = ropaService.obtenerTodasLasRopas();

			model.addAttribute("listaRopas", listaRopas);

			return "sesionusu";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}



	private String guardarImagen(MultipartFile imagen) throws IOException {

		String nombreImagen = imagen.getOriginalFilename();
		String rutaDestino = "./src/main/resources/static/imagenropa/";
		Path directorioDestino = Paths.get(rutaDestino);
		if (!Files.exists(directorioDestino)) {
			Files.createDirectories(directorioDestino);
		}
		byte[] bytesImagen = imagen.getBytes();
		Path rutaArchivo = Paths.get(rutaDestino + nombreImagen);
		Files.write(rutaArchivo, bytesImagen);
		return nombreImagen;
	}




}


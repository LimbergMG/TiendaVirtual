package com.example.tienda.controller;

import com.example.tienda.entity.producto.Ropa;
import com.example.tienda.service.RopaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private RopaServiceImpl ropaService;

	@GetMapping
	public String getHome() {
		return "Home";
	}

	@GetMapping("/producto")
	public String getProductoPage(Model model) {
		List<Ropa> listaRopas = ropaService.obtenerTodasLasRopas();
		model.addAttribute("listaRopas", listaRopas);
		return "producto";
	}
	
}

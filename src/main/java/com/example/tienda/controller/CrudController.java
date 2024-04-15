package com.example.tienda.controller;

import com.example.tienda.entity.producto.Ropa;
import com.example.tienda.service.RopaService;
import com.example.tienda.service.RopaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/crud")
@RequiredArgsConstructor
public class CrudController {

    private final RopaServiceImpl ropaService;

    @GetMapping
    public String mostrarcrud(Model model,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam(name = "error", required = false) String error) {
        if (error != null && userDetails == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }

        if (userDetails != null) {
            // Cargar la lista de productos
            List<Ropa> listaRopas = ropaService.obtenerTodasLasRopas();
            model.addAttribute("listaRopas", listaRopas);

            String email = userDetails.getUsername();
            model.addAttribute("userLogin", email);
            return "sesionusu";
        }

        return "login";
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
        // Obtener solo el nombre de archivo de la imagen
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


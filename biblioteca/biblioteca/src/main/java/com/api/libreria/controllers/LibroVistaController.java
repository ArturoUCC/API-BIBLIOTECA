package com.api.libreria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.libreria.models.LibroModel;
import com.api.libreria.services.LibroService;

@Controller
public class LibroVistaController {

    private final LibroService libroService;

    public LibroVistaController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping({ "/", "/vista/libros" })
    public String mostrarLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerLibros());
        if (!model.containsAttribute("libro")) {
            model.addAttribute("libro", new LibroModel());
        }
        return "libros";
    }

    @PostMapping("/vista/libros/guardar")
    public String guardarLibro(@ModelAttribute LibroModel libro, RedirectAttributes atributos) {
        libroService.guardarLibro(libro);
        atributos.addFlashAttribute("mensaje", "Libro registrado correctamente.");
        return "redirect:/vista/libros";
    }

    @PostMapping("/vista/libros/{isbn}/actualizar")
    public String actualizarLibro(@PathVariable Long isbn, @ModelAttribute LibroModel libro,
        RedirectAttributes redirectAttributes) {
    if (libroService.obtenerLibroPorIsbn(isbn).isEmpty()) {
        redirectAttributes.addFlashAttribute("error", "No se encontró el libro con ISBN " + isbn);
        return "redirect:/vista/libros";
    }
    libroService.actualizarLibro(isbn, libro);
    redirectAttributes.addFlashAttribute("mensaje", "Libro actualizado correctamente");
    return "redirect:/vista/libros";
}

    @PostMapping("/vista/libros/{isbn}/eliminar")
    public String eliminarLibro(@PathVariable Long isbn, RedirectAttributes redirectAttributes) {
    boolean eliminado = libroService.eliminarLibro(isbn);
    if (eliminado) {
        redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado correctamente");
    } else {
        redirectAttributes.addFlashAttribute("error", "No se encontró el libro con ISBN " + isbn);
    }
    return "redirect:/vista/libros";
}

    
}
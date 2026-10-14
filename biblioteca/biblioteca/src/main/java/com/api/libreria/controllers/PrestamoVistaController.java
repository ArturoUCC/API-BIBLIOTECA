package com.api.libreria.controllers;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.services.ClienteService;
import com.api.libreria.services.LibroService;
import com.api.libreria.services.PrestamoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/vista/prestamos")
public class PrestamoVistaController {

    private final PrestamoService prestamoService;
    private final ClienteService clienteService;
    private final LibroService libroService;

    public PrestamoVistaController(PrestamoService prestamoService,
                                   ClienteService clienteService,
                                   LibroService libroService) {
        this.prestamoService = prestamoService;
        this.clienteService = clienteService;
        this.libroService = libroService;
    }

    @GetMapping
    public String listarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoService.listarPrestamos());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("libros", libroService.obtenerLibros());
        model.addAttribute("prestamo", new PrestamoModel());
        model.addAttribute("hoy", LocalDate.now());   // para el atributo min de los campos de fecha
        return "prestamos";
    }

    @PostMapping("/guardar")
    public String registrarPrestamo(@ModelAttribute("prestamo") PrestamoModel prestamo,
                                    RedirectAttributes redirectAttributes) {
        LocalDate hoy = LocalDate.now();

        if (prestamo.getFechaPrestamo() == null || prestamo.getFechaDevolucion() == null) {
            redirectAttributes.addFlashAttribute("error", "Debes indicar ambas fechas.");
            return "redirect:/vista/prestamos";
        }
        if (prestamo.getFechaPrestamo().isBefore(hoy)) {
            redirectAttributes.addFlashAttribute("error", "La fecha de préstamo no puede ser anterior a hoy.");
            return "redirect:/vista/prestamos";
        }
        if (prestamo.getFechaDevolucion().isBefore(prestamo.getFechaPrestamo())) {
            redirectAttributes.addFlashAttribute("error", "La fecha de devolución no puede ser anterior a la fecha de préstamo.");
            return "redirect:/vista/prestamos";
        }

        prestamoService.registrarPrestamo(prestamo);
        redirectAttributes.addFlashAttribute("mensaje", "Préstamo registrado correctamente.");
        return "redirect:/vista/prestamos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
        return "redirect:/vista/prestamos";
    }
}
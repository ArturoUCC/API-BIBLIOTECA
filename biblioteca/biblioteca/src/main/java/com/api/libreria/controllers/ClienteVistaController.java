package com.api.libreria.controllers;

import com.api.libreria.models.ClienteModel;
import com.api.libreria.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/vista/clientes")
public class ClienteVistaController {

    private final ClienteService clienteService;

    public ClienteVistaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new ClienteModel());
        }
        return "clientes";
    }

    @PostMapping("/guardar")
    public String registrarCliente(@ModelAttribute("cliente") ClienteModel cliente,
                                   RedirectAttributes redirectAttributes) {
        // El formulario no envía fecha de creación: se asigna al registrar
        cliente.setFechacreacion(LocalDate.now());
        clienteService.registrarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente registrado correctamente.");
        return "redirect:/vista/clientes";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarCliente(@PathVariable Long id,
                                    @ModelAttribute("cliente") ClienteModel cliente,
                                    RedirectAttributes redirectAttributes) {
        ClienteModel existente = clienteService.obtenerClientePorId(id);
        if (existente == null) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el cliente con ID " + id);
            return "redirect:/vista/clientes";
        }
        cliente.setId(id);
        // El formulario de edición no envía fecha de creación: se conserva la original
        cliente.setFechacreacion(existente.getFechacreacion());
        clienteService.registrarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado correctamente.");
        return "redirect:/vista/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.eliminarCliente(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado correctamente.");
        return "redirect:/vista/clientes";
    }
}
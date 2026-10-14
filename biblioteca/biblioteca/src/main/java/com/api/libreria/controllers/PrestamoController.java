package com.api.libreria.controllers;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.services.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoModel>> obtenerTodos() {
        return ResponseEntity.ok(prestamoService.listarPrestamos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoModel> obtenerPorId(@PathVariable Long id) {
        PrestamoModel prestamo = prestamoService.obtenerPrestamoPorId(id);
        return prestamo != null ? ResponseEntity.ok(prestamo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PrestamoModel> crear(@RequestBody PrestamoModel prestamo) {
        return ResponseEntity.ok(prestamoService.registrarPrestamo(prestamo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
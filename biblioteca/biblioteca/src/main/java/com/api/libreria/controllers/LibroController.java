package com.api.libreria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.libreria.models.LibroModel;
import com.api.libreria.services.LibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<LibroModel> obtenerTodos() {
        return libroService.obtenerLibros();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LibroModel> obtenerPorId(@PathVariable Long isbn) {
        return libroService.obtenerLibroPorIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibroModel> crear(@RequestBody LibroModel libro) {
        return ResponseEntity.ok(libroService.guardarLibro(libro));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LibroModel> actualizar(@PathVariable Long isbn, @RequestBody LibroModel libro) {
        if (libroService.obtenerLibroPorIsbn(isbn).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libroService.actualizarLibro(isbn, libro));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminar(@PathVariable Long isbn) {
        if (libroService.obtenerLibroPorIsbn(isbn).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminarLibro(isbn);
        return ResponseEntity.ok().build();
    }

}
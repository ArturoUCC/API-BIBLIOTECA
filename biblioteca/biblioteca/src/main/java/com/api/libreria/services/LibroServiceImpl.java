package com.api.libreria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.libreria.models.LibroModel;
import com.api.libreria.repositories.ILibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public List<LibroModel> obtenerLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<LibroModel> obtenerLibroPorIsbn(Long isbn) {
        return libroRepository.findById(isbn);
    }

    @Override
    public LibroModel guardarLibro(LibroModel libro) {
        return libroRepository.save(libro);
    }

    @Override
    public LibroModel actualizarLibro(Long isbn, LibroModel libro) {
        libro.setIsbn(isbn);
        return libroRepository.save(libro);
    }

    @Override
    public boolean eliminarLibro(Long isbn) {
        if (libroRepository.existsById(isbn)) {
            libroRepository.deleteById(isbn);
            return true;
        }
    return false;
}

}
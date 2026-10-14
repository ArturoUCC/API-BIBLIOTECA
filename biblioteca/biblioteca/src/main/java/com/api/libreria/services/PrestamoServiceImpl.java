package com.api.libreria.services;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.repositories.IPrestamoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final IPrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(IPrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public List<PrestamoModel> listarPrestamos() {
        return (List<PrestamoModel>) prestamoRepository.findAll();
    }

    @Override
    public PrestamoModel registrarPrestamo(PrestamoModel prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public PrestamoModel obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }
}
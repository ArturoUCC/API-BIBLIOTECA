package com.api.libreria.services;

import com.api.libreria.models.PrestamoModel;
import java.util.List;

public interface PrestamoService {
    List<PrestamoModel> listarPrestamos();
    PrestamoModel registrarPrestamo(PrestamoModel prestamo);
    PrestamoModel obtenerPrestamoPorId(Long id);
    void eliminarPrestamo(Long id);
}
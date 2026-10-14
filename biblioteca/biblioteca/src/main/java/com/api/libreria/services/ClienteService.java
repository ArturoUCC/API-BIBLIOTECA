package com.api.libreria.services;

import com.api.libreria.models.ClienteModel;
import java.util.List;

public interface ClienteService {
    List<ClienteModel> listarClientes();
    ClienteModel registrarCliente(ClienteModel cliente);
    ClienteModel obtenerClientePorId(Long id);
    void eliminarCliente(Long id);
}
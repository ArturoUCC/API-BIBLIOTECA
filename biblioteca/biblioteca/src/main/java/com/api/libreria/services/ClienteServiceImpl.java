package com.api.libreria.services;

import com.api.libreria.models.ClienteModel;
import com.api.libreria.repositories.IClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteServiceImpl(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteModel> listarClientes() {
        return (List<ClienteModel>) clienteRepository.findAll();
    }

    @Override
    public ClienteModel registrarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteModel obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
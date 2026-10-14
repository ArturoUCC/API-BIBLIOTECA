package com.api.libreria.repositories;

import com.api.libreria.models.ClienteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends CrudRepository<ClienteModel, Long> {
}
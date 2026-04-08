package com.example.repositories;

import com.example.models.PedidoAluguel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PedidoAluguelRepository extends CrudRepository<PedidoAluguel, Long> {}
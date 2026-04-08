package com.example.repositories;

import com.example.models.Automovel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AutomovelRepository extends CrudRepository<Automovel, Long> {}
package com.example.services;

import com.example.models.Cliente;
import com.example.repositories.ClienteRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional; // Adicione este import
import java.util.List;

@Singleton
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return (List<Cliente>) repository.findAll();
    }
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(new Cliente());
    }
    @Transactional // <-- ADICIONE ISSO AQUI PARA GARANTIR A GRAVAÇÃO
    public Cliente salvar(Cliente cliente) {
        if (cliente.getId() == null) {
            return repository.save(cliente);
        }
        return repository.update(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
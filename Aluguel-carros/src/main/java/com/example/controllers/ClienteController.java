package com.example.controllers;

import com.example.models.Cliente;
import com.example.services.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;
import java.net.URI;
import java.util.Map;

@Controller("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // 1. LISTAR: Abre a tabela com todos os clientes
    @Get
    @View("clientes/lista")
    public Map<String, Object> listar() {
        return Map.of("clientes", clienteService.listarTodos());
    }

    // 2. NOVO: Abre o formulário vazio para cadastro
    @Get("/novo")
    @View("clientes/form")
    public Map<String, Object> novoForm() {
        return Map.of("cliente", new Cliente());
    }

    // 3. EDITAR: Abre o formulário preenchido com dados de um cliente existente
    @Get("/editar/{id}")
    @View("clientes/form")
    public Map<String, Object> editar(@PathVariable Long id) {
        return Map.of("cliente", clienteService.buscarPorId(id));
    }

    // 4. SALVAR: Recebe os dados do formulário (Novo ou Editado) e grava no banco
    @Post(value = "/salvar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> salvar(@Body Cliente cliente) {
        clienteService.salvar(cliente);
        // Redireciona para a lista após salvar
        return HttpResponse.seeOther(URI.create("/clientes"));
    }

    // 5. DELETAR: Remove o cliente e volta para a lista
    @Get("/deletar/{id}")
    public HttpResponse<?> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return HttpResponse.seeOther(URI.create("/clientes"));
    }
}
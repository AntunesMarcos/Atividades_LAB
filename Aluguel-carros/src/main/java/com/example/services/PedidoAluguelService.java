package com.example.services;

import com.example.models.Automovel;
import com.example.models.PedidoAluguel;
import com.example.models.StatusAutomovel;
import com.example.repositories.AutomovelRepository;
import com.example.repositories.PedidoAluguelRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.util.List;

@Singleton
public class PedidoAluguelService {
    private final PedidoAluguelRepository aluguelRepo;
    private final AutomovelRepository automovelRepo;

    public PedidoAluguelService(PedidoAluguelRepository aluguelRepo, AutomovelRepository automovelRepo) {
        this.aluguelRepo = aluguelRepo;
        this.automovelRepo = automovelRepo;
    }

    public List<PedidoAluguel> listarTodos() {
        return (List<PedidoAluguel>) aluguelRepo.findAll();
    }

    @Transactional
    public PedidoAluguel realizarAluguel(PedidoAluguel pedido) {
        Automovel auto = automovelRepo.findById(pedido.getAutomovel().getId())
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));

        if (auto.getStatus() == StatusAutomovel.ALUGADO) {
            throw new IllegalStateException("O carro selecionado já está alugado.");
        }

        auto.setStatus(StatusAutomovel.ALUGADO);
        automovelRepo.update(auto);
        return aluguelRepo.save(pedido);
    }
}
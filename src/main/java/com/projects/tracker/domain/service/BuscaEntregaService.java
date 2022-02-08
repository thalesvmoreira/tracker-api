package com.projects.tracker.domain.service;

import com.projects.tracker.domain.exception.EntidadeNaoEncontradaExcepction;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepction("Entrega não encontrada."));
    }
}

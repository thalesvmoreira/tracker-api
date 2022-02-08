package com.projects.tracker.domain.service;

import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.model.StatusEntrega;
import com.projects.tracker.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizacaoEntregaService {

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @Autowired
    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}

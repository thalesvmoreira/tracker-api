package com.projects.tracker.domain.service;

import com.projects.tracker.domain.model.Cliente;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.model.StatusEntrega;
import com.projects.tracker.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

    @Transactional
    public void excluir(Long entregaId) {
        entregaRepository.deleteById(entregaId);
    }

}

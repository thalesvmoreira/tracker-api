package com.projects.tracker.domain.service;

import com.projects.tracker.domain.exception.NegocioException;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.model.Ocorrencia;
import com.projects.tracker.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroOcorrenciaService {

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

}

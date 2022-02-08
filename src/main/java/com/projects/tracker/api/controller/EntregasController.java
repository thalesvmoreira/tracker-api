package com.projects.tracker.api.controller;

import com.projects.tracker.api.assembler.EntregaAssembler;
import com.projects.tracker.api.model.EntregaDTO;
import com.projects.tracker.api.model.input.EntregaInput;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.repository.EntregaRepository;
import com.projects.tracker.domain.service.FinalizacaoEntregaService;
import com.projects.tracker.domain.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregasController {

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);

        return entregaAssembler.toDTO(solicitacaoEntregaService.solicitar(novaEntrega));
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

}

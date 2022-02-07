package com.projects.tracker.api.controller;

import com.projects.tracker.api.model.DestinatarioDTO;
import com.projects.tracker.api.model.EntregaDTO;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.repository.EntregaRepository;
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
    private EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
                    EntregaDTO entregaDTO = new EntregaDTO();

                    entregaDTO.setId(entrega.getId());
                    entregaDTO.setNomeCliente(entrega.getCliente().getNome());
                    entregaDTO.setDestinatario(new DestinatarioDTO());
                    entregaDTO.getDestinatario().setNome(entrega.getDestinatario().getNome());
                    entregaDTO.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                    entregaDTO.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                    entregaDTO.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                    entregaDTO.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                    entregaDTO.setStatus(entrega.getStatus());
                    entregaDTO.setTaxa(entrega.getTaxa());
                    entregaDTO.setDataPedido(entrega.getDataPedido());
                    entregaDTO.setDataFinalizacao(entrega.getDataFinalizacao());


                    return ResponseEntity.ok(entregaDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

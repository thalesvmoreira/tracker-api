package com.projects.tracker.api.controller;

import com.projects.tracker.api.assembler.OcorrenciaAssembler;
import com.projects.tracker.api.model.OcorrenciaDTO;
import com.projects.tracker.api.model.input.OcorrenciaInput;
import com.projects.tracker.domain.model.Entrega;
import com.projects.tracker.domain.model.Ocorrencia;
import com.projects.tracker.domain.service.BuscaEntregaService;
import com.projects.tracker.domain.service.RegistroOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciasController {

    @Autowired
    private RegistroOcorrenciaService registroOcorrenciaService;

    @Autowired
    private OcorrenciaAssembler ocorrenciaAssembler;

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionDTO(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId,
                                   @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toDTO(ocorrenciaRegistrada);
    }
}

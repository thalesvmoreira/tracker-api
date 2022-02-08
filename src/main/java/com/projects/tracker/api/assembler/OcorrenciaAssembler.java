package com.projects.tracker.api.assembler;

import com.projects.tracker.api.model.OcorrenciaDTO;
import com.projects.tracker.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

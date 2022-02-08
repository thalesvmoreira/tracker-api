package com.projects.tracker.api.assembler;

import com.projects.tracker.api.model.EntregaDTO;
import com.projects.tracker.api.model.input.EntregaInput;
import com.projects.tracker.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public EntregaDTO toDTO(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

}

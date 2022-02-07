package com.projects.tracker.api.model;

import com.projects.tracker.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaDTO {
    private Long id;
    private String nomeCliente;
    private DestinatarioDTO destinatario;
    private StatusEntrega status;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}

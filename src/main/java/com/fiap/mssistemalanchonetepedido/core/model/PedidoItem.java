package com.fiap.mssistemalanchonetepedido.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItem {

    private String id;

    private String pedidoId;

    private String produtoId;

    private String comboId;

    private Integer quantidade;

    private BigDecimal precoUnitario;

}

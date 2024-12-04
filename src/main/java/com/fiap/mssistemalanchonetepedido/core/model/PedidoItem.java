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

    private String codigoPedido;

    private String produtoId;

    private String comboId;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    public PedidoItem(String codigoPedido, String produtoId, String comboId, Integer quantidade, BigDecimal precoUnitario) {
        this.codigoPedido = codigoPedido;
        this.produtoId = produtoId;
        this.comboId = comboId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}

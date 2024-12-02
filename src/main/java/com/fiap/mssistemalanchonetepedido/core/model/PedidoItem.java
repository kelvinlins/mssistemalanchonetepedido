package com.fiap.mssistemalanchonetepedido.core.model;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
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

    private String pedidoId;

    private String produtoId; // ID do produto individual (null se for combo)

    private String comboId; // ID do combo (null se for produto individual)

    private Integer quantidade; // Quantidade do item

    private BigDecimal precoUnitario; // Preço unitário do ite

}

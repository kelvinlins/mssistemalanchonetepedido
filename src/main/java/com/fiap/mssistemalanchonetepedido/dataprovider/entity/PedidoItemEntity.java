package com.fiap.mssistemalanchonetepedido.dataprovider.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "pedido_item")
public class PedidoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    @Column(name = "produto_id")
    private String produtoId; // ID do produto individual

    @Column(name = "combo_id")
    private String comboId; // ID do combo

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    /**
     * Método auxiliar para verificar se o item é um produto individual.
     */
    public boolean isProduto() {
        return produtoId != null && comboId == null;
    }

    /**
     * Método auxiliar para verificar se o item é um combo.
     */
    public boolean isCombo() {
        return comboId != null && produtoId == null;
    }
}

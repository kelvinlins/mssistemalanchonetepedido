package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import java.math.BigDecimal;

public record ItemComboDto (
  String codigo,
  String nome,
  String descricao,
  String categoria,
  Integer quantidade,
  BigDecimal preco
) {
}

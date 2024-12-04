package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import com.fiap.mssistemalanchonetepedido.core.model.Produto;

import java.util.HashMap;
import java.util.Map;

public record AdicionarProdutoRequestDto(
  String codigoProduto,

  Integer valor,

  Integer quantidade

) {
  public Map<Produto, Integer> toMapItem() {
    Map<Produto, Integer> map = new HashMap<>();
    map.put(
      Produto.builder()
        .codigo(codigoProduto())
        .build(),
      quantidade()
    );
    return null;
  }

}

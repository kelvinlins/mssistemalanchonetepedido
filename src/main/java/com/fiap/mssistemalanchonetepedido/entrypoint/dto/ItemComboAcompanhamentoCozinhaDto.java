package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

public record ItemComboAcompanhamentoCozinhaDto(
  String codigo,
  String nome,
  String descricao,
  String categoria,
  Integer quantidade
) {
}

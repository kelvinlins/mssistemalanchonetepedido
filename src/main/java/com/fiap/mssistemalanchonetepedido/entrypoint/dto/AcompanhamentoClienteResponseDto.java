package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

public record AcompanhamentoClienteResponseDto(
  String codigo,
  String status,
  String tempoEspera
) {
}

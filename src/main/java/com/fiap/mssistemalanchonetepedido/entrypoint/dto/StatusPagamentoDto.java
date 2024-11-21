package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StatusPagamentoDto(
  String status
) {
}

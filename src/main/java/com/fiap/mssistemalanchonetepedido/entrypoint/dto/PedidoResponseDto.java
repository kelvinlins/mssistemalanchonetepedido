package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PedidoResponseDto(
  String codigo,
  String status,
  List<ComboDto> combos,

  List<PedidoItem> pedidoItens,

  BigDecimal total,
  LocalDateTime horaCheckout,
  String tempoEspera,
  String cliente
) {
}

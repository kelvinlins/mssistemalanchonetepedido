package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;

public record AtualizaPedidoRequestDto(
  StatusPedidoEnum status
) {
}

package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoRequestDto{
  String codigoPedido;
}

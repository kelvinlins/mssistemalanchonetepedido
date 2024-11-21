package com.fiap.mssistemalanchonetepedido.entrypoint.dto;

import java.util.List;

public record CriarComboRequestDto (
  List<AdicionarProdutoRequestDto> itens
){}

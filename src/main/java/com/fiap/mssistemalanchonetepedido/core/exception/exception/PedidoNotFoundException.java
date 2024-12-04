package com.fiap.mssistemalanchonetepedido.core.exception.exception;

public class PedidoNotFoundException extends NotFoundException{
  public PedidoNotFoundException() {
    super("Pedido Não Encontrado!");
  }
}

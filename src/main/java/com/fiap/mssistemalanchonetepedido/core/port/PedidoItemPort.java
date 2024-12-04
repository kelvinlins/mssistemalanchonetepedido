package com.fiap.mssistemalanchonetepedido.core.port;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;

import java.util.List;

public interface PedidoItemPort {

    List<PedidoItem> criaPedidoItem(String pedidoId, Pedido pedidoParaAtualizar);
}

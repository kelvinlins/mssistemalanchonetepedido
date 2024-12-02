package com.fiap.mssistemalanchonetepedido.core.port;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;

public interface PedidoItemPort {

    PedidoItem criaPedidoItem(PedidoItem pedidoItem);
}

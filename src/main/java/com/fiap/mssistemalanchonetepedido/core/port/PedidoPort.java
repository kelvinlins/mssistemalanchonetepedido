package com.fiap.mssistemalanchonetepedido.core.port;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoPort {
    Pedido salvarPedido(Pedido pedido);
    Pedido atualizarPedido(Pedido pedido);
    Pedido consultarPedidoPorCodigo(String codigo);
    void deletarPedido(Pedido pedido);
    Page<Pedido> listarPedidosPorStatus(Pageable pageable, List<StatusPedidoEnum> statusList);
    Page<Pedido> listarPedidos(Pageable pageable);
}

package com.fiap.mssistemalanchonetepedido.core.usecase;

import com.fiap.mssistemalanchonetepedido.core.enums.StatusPagamentoEnum;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoUseCaseFacade {

    Pedido criarPedido(Pedido pedido) throws Exception;

    Pedido atualizarPedido(Pedido pedido, String codigo) throws Exception;

    Page<Pedido> listarPedidos(Pageable pageable) throws Exception;

    void deletarPedido(String codigo) throws Exception;

    Pedido adicionarProdutos(String codigoPedido, Integer codigoCombo, String codigoProduto, Integer quantidade) throws Exception;

    Pedido removerProduto(String codigoPedido, Integer comboId, String codigoProduto, Integer quantidade) throws Exception;

    Page<Pedido> listarPedidosPorStatus(Pageable pageable, List<StatusPedidoEnum> status) throws Exception;

    Pedido adicionarCombo(String codigoPedido, Pedido pedidoParaAtualizar) throws Exception;

    Pedido getPedidoPorCodigo(String codigoPedido);

    void deletarCombo(String codigoPedido, Integer comboId) throws Exception;

    Pedido checkout(String codigoPedido) throws Exception;

    StatusPagamentoEnum getStatusPagamento(String codigoPedido);

}

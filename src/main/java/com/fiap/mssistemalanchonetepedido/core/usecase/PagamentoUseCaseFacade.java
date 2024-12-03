package com.fiap.mssistemalanchonetepedido.core.usecase;

import com.fiap.mssistemalanchonetepedido.core.model.Pagamento;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;

public interface PagamentoUseCaseFacade {
    Pedido realizarPagamento(String codigoPedido);
}

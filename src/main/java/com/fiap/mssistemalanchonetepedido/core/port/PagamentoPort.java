package com.fiap.mssistemalanchonetepedido.core.port;

import com.fiap.mssistemalanchonetepedido.core.model.Pagamento;

public interface PagamentoPort {
    Pagamento realizarPagamento(String codigoPedido);
}

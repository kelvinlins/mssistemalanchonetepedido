package com.fiap.mssistemalanchonetepedido.dataprovider.service;

import com.fiap.mssistemalanchonetepedido.core.model.Pagamento;
import com.fiap.mssistemalanchonetepedido.core.port.PagamentoPort;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService implements PagamentoPort {
    @Override
    public Pagamento realizarPagamento(String codigoPedido) {


        return Pagamento.builder() //retorno mockado sem integração
                .aprovado(Boolean.TRUE)
                .build();
    }
}

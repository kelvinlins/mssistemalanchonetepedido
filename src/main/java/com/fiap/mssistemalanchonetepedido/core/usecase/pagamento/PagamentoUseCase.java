package com.fiap.mssistemalanchonetepedido.core.usecase.pagamento;

import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoPagoException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoSemProdutosException;
import com.fiap.mssistemalanchonetepedido.core.model.Pagamento;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.port.PagamentoPort;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoPort;
import com.fiap.mssistemalanchonetepedido.core.usecase.PagamentoUseCaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class PagamentoUseCase implements PagamentoUseCaseFacade {

    private final PagamentoPort pagamentoPort;
    private final PedidoPort pedidoPort;

    @Autowired
    public PagamentoUseCase(PagamentoPort pagamentoPort, PedidoPort pedidoPort) {
        this.pagamentoPort = pagamentoPort;
        this.pedidoPort = pedidoPort;
    }

    @Override
    public Pedido realizarPagamento(String codigoPedido) {
        Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);

        if (ObjectUtils.isEmpty(pedido))
            throw new PedidoNotFoundException();

        if (!ObjectUtils.isEmpty(pedido.getHoraCheckout())){
            throw new PedidoPagoException();
        }

        pedido.setStatus(StatusPedidoEnum.PAGO);

        return pedidoPort.salvarPedido(pedido);
    }

}

package com.fiap.mssistemalanchonetepedido.core.validation.pedido;

import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoAguardandoPagamentoException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoSemProdutosException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.QuantidadeInvalidaException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.StatusInvalidoException;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class PedidoValidation {


    public PedidoValidation() {
    }

    public void validarPedido(Pedido pedido) throws Exception {
        if (Objects.nonNull(pedido.getCliente())){
        }
    }


    public void validarQuantidade(Integer quantidade) {
        if(Objects.isNull(quantidade) || quantidade <1){
            throw new QuantidadeInvalidaException();
        }
    }

    public void validarStatusAlteracaoCombo(Pedido pedido) {
        if (!StatusPedidoEnum.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())){
            throw new StatusInvalidoException();
        }
    }

    public void validarAlteracaoCombosPedido(Pedido pedido, String codigoProduto, Integer quantidade) {
        validarQuantidade(quantidade);
    }

    public void validarStatusPedidoPago(Pedido pedido) {
        if (!StatusPedidoEnum.PAGO.equals(pedido.getStatus())){
            throw new PedidoAguardandoPagamentoException();
        }
    }

}

package com.fiap.mssistemalanchonetepedido.core.usecase.pedido;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPagamentoEnum;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.ComboNotFoundException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoItemPort;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoPort;
import com.fiap.mssistemalanchonetepedido.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonetepedido.core.validation.pedido.PedidoValidation;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.PedidoItemMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PedidoUseCase implements PedidoUseCaseFacade {

  private final PedidoPort pedidoPort;

  private final PedidoItemPort pedidoItemPort;
  private final PedidoValidation validation;

  private final PedidoItemMapper pedidoItemMapper;

  @Autowired
  public PedidoUseCase(PedidoPort pedidoPort, PedidoItemPort pedidoItemPort, PedidoValidation validation, PedidoItemMapper pedidoItemMapper){
    this.pedidoPort = pedidoPort;
    this.pedidoItemPort = pedidoItemPort;
    this.validation = validation;
    this.pedidoItemMapper = pedidoItemMapper;
  }

  @Override
  public Pedido criarPedido(Pedido pedido) throws Exception {
    validation.validarPedido(pedido);
    pedido.setCodigo(UUID.randomUUID().toString());
    pedido.setStatus(StatusPedidoEnum.AGUARDANDO_PAGAMENTO);
    return pedidoPort.salvarPedido(pedido);
  }

  @Override
  public Pedido atualizarPedido(Pedido pedido, String codigo) throws Exception {
    getPedidoPorCodigo(codigo);
    pedido.setCodigo(codigo);
    return pedidoPort.atualizarPedido(pedido);
  }

  @Override
  public Page<Pedido> listarPedidos(Pageable pageable) throws Exception {
    return pedidoPort.listarPedidos(pageable);
  }

  @Override
  public void deletarPedido(String codigo) throws Exception {
    Pedido pedido = getPedidoPorCodigo(codigo);
    pedidoPort.deletarPedido(pedido);
  }

  @Override
  public Pedido adicionarProdutos(String codigoPedido, Integer codigoCombo, String codigoProduto, Integer quantidade) throws Exception {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarAlteracaoCombosPedido(pedido, codigoProduto, quantidade);


    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Pedido removerProduto(String codigoPedido, Integer comboId, String codigoProduto, Integer quantidade) throws Exception {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarAlteracaoCombosPedido(pedido, codigoProduto, quantidade);


    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Page<Pedido> listarPedidosPorStatus(Pageable pageable, List<StatusPedidoEnum> status) {
    return pedidoPort.listarPedidosPorStatus(pageable, status);
  }

  @Override
  public Pedido adicionarCombo(String codigoPedido, Pedido pedidoParaAtualizar) {

    List<PedidoItem> pedidoItems = pedidoItemPort.criaPedidoItem(codigoPedido, pedidoParaAtualizar);

    return getPedidoPorCodigo(codigoPedido);

  }

  @Override
  public Pedido getPedidoPorCodigo(String codigoPedido) {

    Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);

    if (ObjectUtils.isEmpty(pedido))
      throw new PedidoNotFoundException();

    return pedido;
  }

  @Override
  public Pedido checkout(String codigoPedido) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusPedidoPago(pedido);

    pedido.setHoraCheckout(LocalDateTime.now());
    pedido.setStatus(StatusPedidoEnum.RECEBIDO);

    return pedidoPort.atualizarPedido(pedido);
  }

  @Override
  public StatusPagamentoEnum getStatusPagamento(String codigoPedido) {
    Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);

    if (ObjectUtils.isEmpty(pedido))
      throw new PedidoNotFoundException();

    return pedido.getStatus().equals(StatusPedidoEnum.AGUARDANDO_PAGAMENTO) ? StatusPagamentoEnum.NEGADO : StatusPagamentoEnum.APROVADO;
  }

  @Override
  public void deletarCombo(String codigoPedido, Integer comboId) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusAlteracaoCombo(pedido);

    if (codigoPedido.isEmpty()){
      /* var foiRemovido = pedido.getCombos().removeIf(combo -> comboId.equals(combo.getId()));
      if (Boolean.FALSE.equals(foiRemovido)){
        throw new ComboNotFoundException();
      }
       */
      pedidoPort.atualizarPedido(pedido);
    } else {
      throw new ComboNotFoundException();
    }
  }
}

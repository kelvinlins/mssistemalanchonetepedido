package com.fiap.mssistemalanchonetepedido.core.usecase.pedido;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPagamentoEnum;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.ComboNotFoundException;
import com.fiap.mssistemalanchonetepedido.core.exception.exception.PedidoNotFoundException;
import com.fiap.mssistemalanchonetepedido.core.model.Combo;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoItemPort;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoPort;
import com.fiap.mssistemalanchonetepedido.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonetepedido.core.validation.pedido.PedidoValidation;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PedidoUseCase implements PedidoUseCaseFacade {

  private final PedidoPort pedidoPort;

  private final PedidoItemPort pedidoItemPort;
  private final PedidoValidation validation;

  @Autowired
  public PedidoUseCase(PedidoPort pedidoPort, PedidoItemPort pedidoItemPort, PedidoValidation validation){
    this.pedidoPort = pedidoPort;
    this.pedidoItemPort = pedidoItemPort;
    this.validation = validation;
  }

  @Override
  public Pedido criarPedido(Pedido pedido) throws Exception {
    validation.validarPedido(pedido);
    pedido.setCodigo(UUID.randomUUID().toString());
    pedido.setStatus(StatusPedidoEnum.AGUARDANDO_PAGAMENTO);
    pedido.setCombos(List.of(Combo.builder().id(1).build()));
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

    pedido.getCombos().stream()
            .filter(combo -> codigoCombo.equals(combo.getId()))
            .findFirst()
            .ifPresentOrElse(
                    combo -> {
                      var produtoKey = combo.getItens().keySet().stream()
                              .filter(produto ->StringUtils.equals(produto.getCodigo(), codigoProduto))
                              .findFirst();
                      produtoKey.ifPresentOrElse(
                              produto -> combo.getItens().put(produto, combo.getItens().get(produto)+quantidade),
                              () -> combo.getItens().put(Produto.builder().codigo(codigoProduto).build(), quantidade)
                      );
                    },
                    () -> {
                      throw new ComboNotFoundException();
                    }
            );

    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Pedido removerProduto(String codigoPedido, Integer comboId, String codigoProduto, Integer quantidade) throws Exception {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarAlteracaoCombosPedido(pedido, codigoProduto, quantidade);

    pedido.getCombos().stream()
            .filter(combo -> comboId.equals(combo.getId()))
            .findFirst()
            .ifPresentOrElse(
                    combo -> {
                      var produtoKey = combo.getItens().keySet().stream()
                              .filter(produto ->StringUtils.equals(produto.getCodigo(), codigoProduto))
                              .findFirst();
                      produtoKey.ifPresent(
                              produto -> {
                                if (quantidade<combo.getItens().get(produto)){
                                  combo.getItens().put(produto, combo.getItens().get(produto)-quantidade);
                                } else {
                                  combo.getItens().remove(produto);
                                }
                              }
                      );
                    },
                    () -> {
                      throw new ComboNotFoundException();
                    }
            );
    return atualizarPedido(pedido, codigoPedido);
  }

  @Override
  public Page<Pedido> listarPedidosPorStatus(Pageable pageable, List<StatusPedidoEnum> status) {
    return pedidoPort.listarPedidosPorStatus(pageable, status);
  }

  @Override
  public Integer adicionarCombo(String codigoPedido, Pedido pedidoParaAtualizar) {
    var pedido = getPedidoPorCodigo(codigoPedido);


    PedidoItem pedidoItemtest = new PedidoItem(
            "33a5d79b-9d2c-4e2f-b859-5d436ab8e678",
            "3359a45-f8f7-4b8d-9dd2-a92c51c6c912",
            "3359a45-f8f7-4b8d-9dd2-a92c51c6c911",
            1,
            new BigDecimal(10));

    System.out.println(pedidoItemPort.criaPedidoItem(pedidoItemtest));

    validation.validarStatusAlteracaoCombo(pedido);
    validation.validarCombos(pedidoParaAtualizar.getCombos());

    var maiorComboId = pedido.getCombos().stream()
            .filter(Objects::nonNull)
            .map(Combo::getId)
            .max(Integer::compareTo);

    Combo comboParaAdiconar = getComboParaAdiconar(pedidoParaAtualizar);

    comboParaAdiconar.setId(1 + maiorComboId.orElse(0));
    pedido.getCombos().add(comboParaAdiconar);
    pedidoPort.atualizarPedido(pedido);
    return comboParaAdiconar.getId();
  }

  @Override
  public Pedido getPedidoPorCodigo(String codigoPedido) {

    Pedido pedido = pedidoPort.consultarPedidoPorCodigo(codigoPedido);

    if (ObjectUtils.isEmpty(pedido))
      throw new PedidoNotFoundException();

    return pedido;
  }

  private static Combo getComboParaAdiconar(Pedido pedidoParaAtualizar) {
    return Objects.isNull(pedidoParaAtualizar.getCombos()) || pedidoParaAtualizar.getCombos().isEmpty() ?
            new Combo() :
            pedidoParaAtualizar.getCombos().get(0);
  }

  @Override
  public Pedido checkout(String codigoPedido) {
    var pedido = getPedidoPorCodigo(codigoPedido);

    validation.validarStatusPedidoPago(pedido);
    validation.validarQuePedidoTemProdutos(pedido);

    pedido.getCombos().removeIf(Combo::semProdutos);
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

    if (Objects.nonNull(pedido.getCombos())){
      var foiRemovido = pedido.getCombos().removeIf(combo -> comboId.equals(combo.getId()));
      if (Boolean.FALSE.equals(foiRemovido)){
        throw new ComboNotFoundException();
      }
      pedidoPort.atualizarPedido(pedido);
    } else {
      throw new ComboNotFoundException();
    }
  }
}

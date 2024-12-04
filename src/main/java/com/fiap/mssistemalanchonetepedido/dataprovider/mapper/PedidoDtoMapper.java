package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.core.model.Cliente;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PedidoDtoMapper {

  @Mapping(target = "cliente", expression = "java(toDomainCliente(dto.codigoCliente()))")
  Pedido toDomain(CriarPedidoRequestDto dto);

  @Mapping(target = "cliente", expression = "java(pedido.getCodigoClienteAsString())")
  PedidoResponseDto toPedidoResponseDto(Pedido pedido);

  Pedido toDomain(AtualizaPedidoRequestDto dto);

  @Mapping(target = "pedidoItens", expression = "java(toDomainCombos(dto.itens()))")
  Pedido toDomain(CriarComboRequestDto dto);

  default List<PedidoItem> toDomainCombos(List<AdicionarProdutoRequestDto> itens){
    if (Objects.isNull(itens)){
      return null;
    }

    List<PedidoItem> pedidos = new ArrayList<>();


    itens.forEach(
            item -> {
              PedidoItem pedido = new PedidoItem();
              pedido.setQuantidade(item.quantidade());
              pedido.setProdutoId(item.codigoProduto());
              pedido.setPrecoUnitario(BigDecimal.valueOf(item.valor()));
              pedidos.add(pedido);
            }
            );

    return pedidos;
  }

  default Cliente toDomainCliente(Long codigoCliente){
    if (ObjectUtils.isEmpty(codigoCliente)){
      return null;
    }
    return Cliente.builder().codigo(codigoCliente).build();
  }

  default Page<AcompanhamentoClienteResponseDto> toAcompanhamentoClienteResponse(Page<Pedido> pedidos){
    return pedidos.map(this::toAcompanhamentoClienteResponse);
  }

  @Mapping(target = "tempoEspera", expression = "java(pedido.getTempoEspera())")
  AcompanhamentoClienteResponseDto toAcompanhamentoClienteResponse(Pedido pedido);

  default Page<AcompanhamentoCozinhaResponseDto> toAcompanhamentoCozinhaResponse(Page<Pedido> pedidos){
    return pedidos.map(this::toAcompanhamentoCozinhaResponse);
  }

  @Mapping(target = "tempoEspera", expression = "java(pedido.getTempoEspera())")
  AcompanhamentoCozinhaResponseDto toAcompanhamentoCozinhaResponse(Pedido pedido);


  default Page<PedidoResponseDto> toPagePedidoResponseDto(Page<Pedido> pedidos){
    return pedidos.map(this::toPedidoResponseDto);
  }

}
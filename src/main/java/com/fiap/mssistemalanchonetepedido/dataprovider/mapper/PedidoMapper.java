package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;


@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  uses = {ProdutoMapper.class}
)
public interface PedidoMapper extends EntityMapper<PedidoEntity, Pedido>{

  @Override
  PedidoEntity toEntity(Pedido domain);

  default Page<Pedido> toDomainPage(Page<PedidoEntity> pedidoEntityPage){
    return pedidoEntityPage.map(this::toDomain);
  }

  @Override
  Pedido toDomain(PedidoEntity entity);
}

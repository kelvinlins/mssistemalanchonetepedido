package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  uses = {ProdutoMapper.class}
)
public interface PedidoMapper extends EntityMapper<PedidoEntity, Pedido>{

  @Override
  PedidoEntity toEntity(Pedido domain);

  List<Pedido> toDomainList(List<PedidoEntity> pedidoEntityList);

  default Page<Pedido> toDomainPage(Page<PedidoEntity> pedidoEntityPage){
    return pedidoEntityPage.map(this::toDomain);
  };

  default Optional<Pedido> toDomain(Optional<PedidoEntity> pedidoEntity){
    return pedidoEntity.map(this::toDomain);
  };
}

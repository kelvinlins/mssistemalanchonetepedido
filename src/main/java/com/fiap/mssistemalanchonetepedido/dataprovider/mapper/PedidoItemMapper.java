package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoItemMapper extends EntityMapper<PedidoItemEntity, PedidoItem>{
}

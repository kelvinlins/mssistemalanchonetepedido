package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.ProdutoEntity;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper extends EntityMapper<ProdutoEntity, Produto>{
}

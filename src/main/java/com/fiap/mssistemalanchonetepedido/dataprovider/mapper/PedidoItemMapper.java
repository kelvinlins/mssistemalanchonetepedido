package com.fiap.mssistemalanchonetepedido.dataprovider.mapper;

import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoItemMapper extends EntityMapper<PedidoItemEntity, PedidoItem>{

    @Override
    @Mapping(target = "pedido", expression = "java(generatePedidoEntity(domain))")
    PedidoItemEntity toEntity(PedidoItem domain);

    default PedidoEntity generatePedidoEntity(PedidoItem domain) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCodigo(domain.getCodigoPedido());

        return pedidoEntity;
    }
}

package com.fiap.mssistemalanchonetepedido.dataprovider.repository;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoItemPort;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.PedidoItemMapper;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.ProdutoMapper;
import com.fiap.mssistemalanchonetepedido.dataprovider.repository.jpa.IPedidoItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class PedidoItemRepository implements PedidoItemPort {

    private final IPedidoItemRepository iPedidoItemRepository;

    private final PedidoItemMapper pedidoItemMapper;

    @Autowired
    public PedidoItemRepository(IPedidoItemRepository iPedidoItemRepository, PedidoItemMapper pedidoItemMapper) {
        this.iPedidoItemRepository = iPedidoItemRepository;
        this.pedidoItemMapper = pedidoItemMapper;
    }

    @Transactional
    @Override
    public PedidoItem criaPedidoItem(PedidoItem pedidoItem) {

        PedidoItemEntity pedido = pedidoItemMapper.toEntity(pedidoItem);

        System.out.println(pedido);

        return pedidoItemMapper.toDomain(iPedidoItemRepository.save(pedidoItemMapper.toEntity(pedidoItem)));

    }

}

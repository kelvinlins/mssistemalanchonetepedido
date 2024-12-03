package com.fiap.mssistemalanchonetepedido.dataprovider.repository;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.model.PedidoItem;
import com.fiap.mssistemalanchonetepedido.core.model.Produto;
import com.fiap.mssistemalanchonetepedido.core.port.PedidoItemPort;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.PedidoItemMapper;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.ProdutoMapper;
import com.fiap.mssistemalanchonetepedido.dataprovider.repository.jpa.IPedidoItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public List<PedidoItem> criaPedidoItem(String pedidoId, Pedido pedidoParaAtualizar) {

        List<PedidoItem> pedidoItems = new ArrayList<>();

        pedidoParaAtualizar.getCombos().stream() // Stream de Combos
                .flatMap(combo -> combo.getItens().entrySet().stream()) // Mapear os itens de cada combo
                .forEach(entry -> {
                    Produto produto = entry.getKey(); // Produto
                    Integer quantidade = entry.getValue(); // quantidade

                    // Exemplo de operação com cada item

                    PedidoItem pedidoItem = new PedidoItem(pedidoId, produto.getCodigo(), "", quantidade, new BigDecimal(10));

                    pedidoItems.add(pedidoItem);

                    pedidoItemMapper.toDomain(iPedidoItemRepository.save(pedidoItemMapper.toEntity(pedidoItem)));

        });

       return pedidoItems;

    }

}

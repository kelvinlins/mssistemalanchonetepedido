package com.fiap.mssistemalanchonetepedido.dataprovider.repository.jpa;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoEntity;
import com.fiap.mssistemalanchonetepedido.dataprovider.entity.PedidoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoItemRepository extends JpaRepository<PedidoItemEntity, String> {
}

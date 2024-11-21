package com.fiap.mssistemalanchonetepedido.dataprovider.repository.jpa;

import com.fiap.mssistemalanchonetepedido.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    List<ProdutoEntity> findByCategoria(String categoria);
}

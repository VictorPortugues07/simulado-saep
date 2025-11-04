package com.produtos.produtos.repositories;

import com.produtos.produtos.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findByCdProduto(Integer cdProduto);
    Optional<ProdutoModel> findAllByCdProduto(Integer cdProduto);
}

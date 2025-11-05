package com.produtos.produtos.repositories;

import com.produtos.produtos.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    @Query("SELECT p FROM ProdutoModel p WHERE p.qtdEstoque <= p.qtdEstoqueMinimo")
    List<ProdutoModel> findProdutosComEstoqueBaixo();

    List<ProdutoModel> findByNmProdutoContainingIgnoreCase(String nmProduto);
}
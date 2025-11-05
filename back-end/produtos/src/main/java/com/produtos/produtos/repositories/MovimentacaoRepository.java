package com.produtos.produtos.repositories;

import com.produtos.produtos.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Long> {
    List<MovimentacaoModel> findByProdutoCdProdutoOrderByDtMovimentacaoDesc(Long cdProduto);
}
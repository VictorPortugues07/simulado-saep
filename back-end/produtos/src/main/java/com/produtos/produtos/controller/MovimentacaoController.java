package com.produtos.produtos.controller;

import com.produtos.produtos.dto.MovimentacaoRecordDto;
import com.produtos.produtos.model.MovimentacaoModel;
import com.produtos.produtos.model.ProdutoModel;
import com.produtos.produtos.repositories.MovimentacaoRepository;
import com.produtos.produtos.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Object> registrarMovimentacao(@RequestBody @Valid MovimentacaoRecordDto dto) {
        Optional<ProdutoModel> produtoOpt = produtoRepository.findById(dto.cdProduto());

        if (produtoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        ProdutoModel produto = produtoOpt.get();
        BigDecimal novoEstoque;

        if ("ENTRADA".equals(dto.tpMovimentacao())) {
            novoEstoque = produto.getQtdEstoque().add(dto.qtdMovimentacao());
        } else {
            novoEstoque = produto.getQtdEstoque().subtract(dto.qtdMovimentacao());
            if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Estoque insuficiente para realizar a saída");
            }
        }

        produto.setQtdEstoque(novoEstoque);
        produtoRepository.save(produto);

        MovimentacaoModel movimentacao = new MovimentacaoModel();
        movimentacao.setProduto(produto);
        movimentacao.setTpMovimentacao(dto.tpMovimentacao());
        movimentacao.setQtdMovimentacao(dto.qtdMovimentacao());
        movimentacao.setDsObservacao(dto.dsObservacao());

        MovimentacaoModel saved = movimentacaoRepository.save(movimentacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoModel>> listarTodas() {
        return ResponseEntity.ok(movimentacaoRepository.findAll());
    }

    @GetMapping("/produto/{cdProduto}")
    public ResponseEntity<List<MovimentacaoModel>> listarPorProduto(@PathVariable Long cdProduto) {
        return ResponseEntity.ok(
                movimentacaoRepository.findByProdutoCdProdutoOrderByDtMovimentacaoDesc(cdProduto)
        );
    }
}
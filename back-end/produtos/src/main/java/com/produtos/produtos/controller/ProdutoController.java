package com.produtos.produtos.controller;

import com.produtos.produtos.dto.ProdutoRecordDto;
import com.produtos.produtos.model.ProdutoModel;
import com.produtos.produtos.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoRecordDto dto) {
        var produto = new ProdutoModel();
        BeanUtils.copyProperties(dto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{cdProduto}")
    public ResponseEntity<Object> buscarProduto(@PathVariable Long cdProduto) {
        Optional<ProdutoModel> produto = produtoRepository.findById(cdProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.ok(produto.get());
    }

    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<ProdutoModel>> listarEstoqueBaixo() {
        return ResponseEntity.ok(produtoRepository.findProdutosComEstoqueBaixo());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoModel>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoRepository.findByNmProdutoContainingIgnoreCase(nome));
    }

    @PutMapping("/{cdProduto}")
    public ResponseEntity<Object> atualizarProduto(
            @PathVariable Long cdProduto,
            @RequestBody @Valid ProdutoRecordDto dto) {
        Optional<ProdutoModel> produtoOpt = produtoRepository.findById(cdProduto);
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produto = produtoOpt.get();
        BeanUtils.copyProperties(dto, produto);
        produto.setCdProduto(cdProduto);
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    @DeleteMapping("/{cdProduto}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long cdProduto) {
        Optional<ProdutoModel> produto = produtoRepository.findById(cdProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        produtoRepository.delete(produto.get());
        return ResponseEntity.ok("Produto deletado com sucesso");
    }
}
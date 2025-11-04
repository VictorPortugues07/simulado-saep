package com.produtos.produtos.controller;


import com.produtos.produtos.dto.ProdutoRecordDto;
import com.produtos.produtos.model.ProdutoModel;
import com.produtos.produtos.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProduto() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

    @GetMapping("{cdProduto}")
    public ResponseEntity<Object> buscarUmProduto(@PathVariable(value="id") int cdProduto) {
        Optional<ProdutoModel> produto0 = produtoRepository.findById(cdProduto);
        if (produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto0.get());
    }

    @PutMapping("/{cdProduto}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value = "id") int cdProduto, @RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        Optional<ProdutoModel> produto0 = produtoRepository.findById(cdProduto);
        if (produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produtoModel = produto0.get();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));
    }

    @DeleteMapping("/{cdProduto}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") int cdProduto) {
        Optional<ProdutoModel> produto0 = produtoRepository.findById(cdProduto);
        if (produto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        produtoRepository.delete(produto0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }
}

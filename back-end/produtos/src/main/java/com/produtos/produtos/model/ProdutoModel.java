package com.produtos.produtos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBPRODUTO")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdProduto;

    private String nmProduto;
    private BigDecimal vlProduto;
    private BigDecimal qtdEstoque;

    private BigDecimal qtdEstoqueMinimo;

    private LocalDateTime dtCadastro;

    @PrePersist
    protected void onCreate() {
        dtCadastro = LocalDateTime.now();
        if (qtdEstoqueMinimo == null) {
            qtdEstoqueMinimo = BigDecimal.ZERO;
        }
    }

    public Long getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Long cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public BigDecimal getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(BigDecimal vlProduto) {
        this.vlProduto = vlProduto;
    }

    public BigDecimal getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(BigDecimal qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public BigDecimal getQtdEstoqueMinimo() {
        return qtdEstoqueMinimo;
    }

    public void setQtdEstoqueMinimo(BigDecimal qtdEstoqueMinimo) {
        this.qtdEstoqueMinimo = qtdEstoqueMinimo;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public boolean isEstoqueBaixo() {
        if (qtdEstoqueMinimo == null || qtdEstoque == null) {
            return false;
        }
        return qtdEstoque.compareTo(qtdEstoqueMinimo) <= 0;
    }
}
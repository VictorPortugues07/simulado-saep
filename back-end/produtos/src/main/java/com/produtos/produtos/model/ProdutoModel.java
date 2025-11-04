package com.produtos.produtos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "TBPRODUTO")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cdProduto;
    private String nmProduto;
    private BigDecimal vlProduto;
    private BigDecimal qtdEstoque;

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
}

package com.produtos.produtos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBMOVIMENTACAO")
public class MovimentacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdMovimentacao;

    @ManyToOne
    @JoinColumn(name = "cd_produto", nullable = false)
    private ProdutoModel produto;

    @Column(nullable = false)
    private String tpMovimentacao;

    @Column(nullable = false)
    private BigDecimal qtdMovimentacao;

    @Column(nullable = false)
    private LocalDateTime dtMovimentacao;

    private String dsObservacao;

    @PrePersist
    protected void onCreate() {
        dtMovimentacao = LocalDateTime.now();
    }

    public Long getCdMovimentacao() {
        return cdMovimentacao;
    }

    public void setCdMovimentacao(Long cdMovimentacao) {
        this.cdMovimentacao = cdMovimentacao;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public String getTpMovimentacao() {
        return tpMovimentacao;
    }

    public void setTpMovimentacao(String tpMovimentacao) {
        this.tpMovimentacao = tpMovimentacao;
    }

    public BigDecimal getQtdMovimentacao() {
        return qtdMovimentacao;
    }

    public void setQtdMovimentacao(BigDecimal qtdMovimentacao) {
        this.qtdMovimentacao = qtdMovimentacao;
    }

    public LocalDateTime getDtMovimentacao() {
        return dtMovimentacao;
    }

    public void setDtMovimentacao(LocalDateTime dtMovimentacao) {
        this.dtMovimentacao = dtMovimentacao;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }
}
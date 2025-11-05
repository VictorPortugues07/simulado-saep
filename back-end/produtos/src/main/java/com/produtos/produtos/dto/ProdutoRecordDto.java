package com.produtos.produtos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoRecordDto(
        @NotBlank String nmProduto,
        @NotNull BigDecimal vlProduto,
        @NotNull BigDecimal qtdEstoque,
        BigDecimal qtdEstoqueMinimo
) {
}
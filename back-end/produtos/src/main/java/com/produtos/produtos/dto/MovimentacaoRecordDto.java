package com.produtos.produtos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public record MovimentacaoRecordDto(
        @NotNull Long cdProduto,
        @NotNull @Pattern(regexp = "ENTRADA|SAIDA") String tpMovimentacao,
        @NotNull BigDecimal qtdMovimentacao,
        String dsObservacao
) {
}
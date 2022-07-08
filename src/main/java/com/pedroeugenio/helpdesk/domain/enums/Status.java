package com.pedroeugenio.helpdesk.domain.enums;

import java.util.Arrays;

public enum Status {

    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private final Integer codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null)
            return null;
        return Arrays.stream(Status.values()).filter(f -> f.codigo.equals(cod)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Status inválido"));

    }

}

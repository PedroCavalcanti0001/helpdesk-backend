package com.pedroeugenio.helpdesk.domain.enums;

import java.util.Arrays;

public enum Prioridade {

    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private final Integer codigo;
    private final String descricao;

    Prioridade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null)
            return null;
        return Arrays.stream(Prioridade.values()).filter(f -> f.codigo.equals(cod)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Prioridade inválido"));

    }

}

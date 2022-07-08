package com.pedroeugenio.helpdesk.domain.enums;

import java.util.Arrays;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

    private final Integer codigo;
    private final String descricao;

    Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null)
            return null;
        return Arrays.stream(Perfil.values()).filter(f -> f.codigo.equals(cod)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Perfil inválido"));

    }

}

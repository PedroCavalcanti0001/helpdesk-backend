package com.pedroeugenio.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedroeugenio.helpdesk.domain.dtos.TecnicoDTO;
import com.pedroeugenio.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(List<Chamado> chamados) {
        this.chamados = chamados;
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico() {

    }

    public Tecnico(TecnicoDTO tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfils = tecnico.getPerfils().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = tecnico.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}

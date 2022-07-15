package com.pedroeugenio.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedroeugenio.helpdesk.domain.dtos.ClienteDTO;
import com.pedroeugenio.helpdesk.domain.dtos.TecnicoDTO;
import com.pedroeugenio.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.perfils = clienteDTO.getPerfils().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = clienteDTO.getDataCriacao();
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}

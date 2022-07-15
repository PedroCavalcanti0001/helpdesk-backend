package com.pedroeugenio.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedroeugenio.helpdesk.domain.Cliente;
import com.pedroeugenio.helpdesk.domain.Tecnico;
import com.pedroeugenio.helpdesk.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O campo nome destacado é requerido.")
    protected String nome;
    @NotNull(message = "O CPF nome destacado é requerido.")
    protected String cpf;
    @NotNull(message = "O email nome destacado é requerido.")
    protected String email;
    @NotNull(message = "O senha nome destacado é requerido.")
    protected String senha;
    protected Set<Integer> perfils = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfils = cliente.getPerfils().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfils() {
        return perfils.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfils.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

package com.pedroeugenio.helpdesk.services;

import com.pedroeugenio.helpdesk.domain.Chamado;
import com.pedroeugenio.helpdesk.domain.Cliente;
import com.pedroeugenio.helpdesk.domain.Tecnico;
import com.pedroeugenio.helpdesk.domain.enums.Perfil;
import com.pedroeugenio.helpdesk.domain.enums.Prioridade;
import com.pedroeugenio.helpdesk.domain.enums.Status;
import com.pedroeugenio.helpdesk.repositories.ChamadoRepository;
import com.pedroeugenio.helpdesk.repositories.ClienteRepository;
import com.pedroeugenio.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "George Henrique", "258.015.240-70", "georgehenrique233@gmail.com", "12345");
        tec1.addPerfil(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Leozinho Vitorio", "188.122.630-11", "leozinhovito@gmail.com","12345");
        tec1.addPerfil(Perfil.ADMIN);


        Cliente cli1 = new Cliente(null, "Linus Torvalds", "824.518.180-34", "linustorvalds@hotmail.com","12345");
        Chamado ch1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);


        Cliente cli2 = new Cliente(null, "Steve Jobs", "490.227.830-81", "stevejobs@protonmail.com", "12345");
        Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 02", "Segundo Chamado", tec2, cli2);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        chamadoRepository.saveAll(Arrays.asList(ch1, ch2));
    }

}
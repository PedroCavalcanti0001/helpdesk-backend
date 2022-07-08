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

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Valdir cezar", "49070454092", "teste@gmail.com", "12345");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "38369702015", "cliente@gmail.com", "12345");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01",
                "Primeiro chamado", tec1, cli1);

        tecnicoRepository.save(tec1);
        clienteRepository.save(cli1);
        chamadoRepository.save(c1);
    }

}

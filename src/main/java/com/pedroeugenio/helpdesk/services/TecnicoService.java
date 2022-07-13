package com.pedroeugenio.helpdesk.services;

import com.pedroeugenio.helpdesk.domain.Tecnico;
import com.pedroeugenio.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElse(null);
    }
}

package com.pedroeugenio.helpdesk.services;

import com.pedroeugenio.helpdesk.domain.Pessoa;
import com.pedroeugenio.helpdesk.domain.Tecnico;
import com.pedroeugenio.helpdesk.domain.dtos.TecnicoDTO;
import com.pedroeugenio.helpdesk.repositories.PessoaRepository;
import com.pedroeugenio.helpdesk.repositories.TecnicoRepository;
import com.pedroeugenio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.pedroeugenio.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico tecnico = new Tecnico(objDTO);
        return tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public Tecnico update(int id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico tecnico = findById(id);
        if(!tecnico.getSenha().equals(tecnicoDTO.getSenha()))
            tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));

        validaPorCpfEEmail(tecnicoDTO);
        tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    public TecnicoDTO delete(int id) {
        Tecnico tecnico = findById(id);
        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
        return new TecnicoDTO(tecnico);
    }
}

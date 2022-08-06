package com.pedroeugenio.helpdesk.services;

import com.pedroeugenio.helpdesk.domain.Pessoa;
import com.pedroeugenio.helpdesk.domain.Cliente;
import com.pedroeugenio.helpdesk.domain.dtos.ClienteDTO;
import com.pedroeugenio.helpdesk.repositories.PessoaRepository;
import com.pedroeugenio.helpdesk.repositories.ClienteRepository;
import com.pedroeugenio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.pedroeugenio.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> tecnico = clienteRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Cliente tecnico = new Cliente(objDTO);
        return clienteRepository.save(tecnico);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public Cliente update(int id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente cliente = findById(id);
        if(!cliente.getSenha().equals(clienteDTO.getSenha()))
            clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        validaPorCpfEEmail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public ClienteDTO delete(int id) {
        Cliente tecnico = findById(id);
        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
        return new ClienteDTO(tecnico);
    }
}

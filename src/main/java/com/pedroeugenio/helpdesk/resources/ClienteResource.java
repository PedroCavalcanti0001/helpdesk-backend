package com.pedroeugenio.helpdesk.resources;

import com.pedroeugenio.helpdesk.domain.Cliente;
import com.pedroeugenio.helpdesk.domain.dtos.ClienteDTO;
import com.pedroeugenio.helpdesk.services.ClienteService;
import com.pedroeugenio.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable int id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(new ClienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
        Cliente tecnico = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable int id, @Valid @RequestBody ClienteDTO tecnico) {
        Cliente obj = service.update(id, tecnico);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

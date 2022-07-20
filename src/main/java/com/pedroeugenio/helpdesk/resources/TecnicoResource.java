package com.pedroeugenio.helpdesk.resources;

import com.pedroeugenio.helpdesk.domain.Tecnico;
import com.pedroeugenio.helpdesk.domain.dtos.TecnicoDTO;
import com.pedroeugenio.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable int id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDto = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        objDTO.setSenha(objDTO.getSenha());
        Tecnico tecnico = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable int id, @Valid @RequestBody TecnicoDTO tecnico) {
        Tecnico obj = service.update(id, tecnico);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

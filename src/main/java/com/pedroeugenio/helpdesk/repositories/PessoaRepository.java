package com.pedroeugenio.helpdesk.repositories;

import com.pedroeugenio.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}

package com.pedroeugenio.helpdesk.repositories;

import com.pedroeugenio.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}

package com.pedroeugenio.helpdesk.repositories;

import com.pedroeugenio.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}

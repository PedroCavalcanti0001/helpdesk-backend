package com.pedroeugenio.helpdesk.repositories;

import com.pedroeugenio.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

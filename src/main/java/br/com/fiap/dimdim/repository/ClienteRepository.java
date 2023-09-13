package br.com.fiap.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dimdim.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}

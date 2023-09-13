package br.com.fiap.dimdim.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dimdim.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Page<Endereco> findByCepContaining(String busca, Pageable pageable);
    
}
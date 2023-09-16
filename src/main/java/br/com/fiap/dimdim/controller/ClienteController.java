package br.com.fiap.dimdim.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dimdim.exception.RestNotFoundException;
import br.com.fiap.dimdim.model.Cliente;
import br.com.fiap.dimdim.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> index(){
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente cliente){
        log.info("cadastrando cliente" + cliente);
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id){
        log.info("detalhando cliente" + id);
        return ResponseEntity.ok(getCliente(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> destroy(@PathVariable Long id){
        log.info("apagando cliente" + id);

        clienteRepository.delete(getCliente(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
        log.info("alterando cliente" + id);
        getCliente(id);
        cliente.setId(id);
        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    private Cliente getCliente(Long id){
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("Empresa não encontrada!"));
    }

}

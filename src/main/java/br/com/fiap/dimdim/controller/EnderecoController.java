package br.com.fiap.dimdim.controller;

import java.util.List;

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
import br.com.fiap.dimdim.model.Endereco;
import br.com.fiap.dimdim.repository.EnderecoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
    
    @Autowired
    EnderecoRepository enderecoRepository;

     @GetMapping
    public List<Endereco> index(){
        return enderecoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Endereco> create(@RequestBody @Valid Endereco endereco){
        enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @GetMapping("{id}")
    public ResponseEntity<Endereco> show(@PathVariable Long id){
        return ResponseEntity.ok(getEndereco(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Endereco> destroy(@PathVariable Long id){

        enderecoRepository.delete(getEndereco(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody @Valid Endereco endereco){
        getEndereco(id);
        endereco.setId(id);
        enderecoRepository.save(endereco); 
        return ResponseEntity.ok(endereco);
    }

    private Endereco getEndereco(Long id){
        return enderecoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("Endereco não encontrada!"));
    }

    
}

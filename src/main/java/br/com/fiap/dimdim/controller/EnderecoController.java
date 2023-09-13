package br.com.fiap.dimdim.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dimdim.exception.RestNotFoundException;
import br.com.fiap.dimdim.model.Endereco;
import br.com.fiap.dimdim.repository.ClienteRepository;
import br.com.fiap.dimdim.repository.EnderecoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
    
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca,
        @ParameterObject @PageableDefault(size=3) Pageable pageable){

            Page<Endereco> enderecos = (busca == null) ?
                enderecoRepository.findAll(pageable):
                enderecoRepository.findByCepContaining(busca, pageable);

            return assembler.toModel(enderecos.map(Endereco::toEntityModel));

        }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Endereco endereco) {
        enderecoRepository.save(endereco);
        endereco.setCliente(clienteRepository.findById(endereco.getCliente().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Endereco> show(@PathVariable Long id){

        var endereco = enderecoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("endereco não encontrado!"));

        return endereco.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Endereco> destroy(@PathVariable Long id){
        var endereco = enderecoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("endereco não encontrado!"));

        enderecoRepository.delete(endereco);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Endereco> update(@PathVariable Long id, @RequestBody @Valid Endereco endereco){

        enderecoRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("endereco não encontrado!"));

        endereco.setId(id);
        enderecoRepository.save(endereco);

        return endereco.toEntityModel();
    }

}

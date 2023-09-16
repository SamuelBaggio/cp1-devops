package br.com.fiap.dimdim.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.dimdim.model.Cliente;
import br.com.fiap.dimdim.model.Endereco;
import br.com.fiap.dimdim.repository.ClienteRepository;
import br.com.fiap.dimdim.repository.EnderecoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente c1 = new Cliente(1l, "Samuel Baggio", "73067845366", "934762831", "samuelsbaggio@gmail.com",
                "11945458181");

        clienteRepository.save(c1);

        enderecoRepository.saveAll(List.of(
                Endereco.builder().cep("09020034")
                    .logradouro("Rua dos Santos")
                    .numero("12A")
                    .bairro("Bairro dos Andes")
                    .cidade("Cidade dos Puros")
                    .estado("SP")
                    .build(),
                Endereco.builder().cep("09023454")
                    .logradouro("Rua dos Anjos")
                    .numero("923")
                    .bairro("Vila Luma")
                    .cidade("Cidade dos Puros")
                    .estado("SP")
                    .build()
        ));
    }

}

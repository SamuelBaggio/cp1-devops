package br.com.fiap.dimdim.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "tb_cliente",
    uniqueConstraints = {
    @UniqueConstraint(
        name = "uk_cpf_cliente",
        columnNames = "cpf_cliente"
    )
    }
)
public class Cliente {
    
    @Id
    @GeneratedValue(
        generator = "seq_cliente",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_cliente",
        sequenceName = "seq_cliente",
        allocationSize = 1
    )
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank
    @Column(name = "nome_cliente")
    private String nome;

    @NotBlank
    @Column(name = "cpf_cliente")
    private String cpf;

    @NotBlank
    @Column(name = "rg_cliente")
    private String rg;

    @Email
    @NotBlank
    @Column(name = "email_cliente")
    private String email;

    @NotBlank
    @Column(name = "tel_cliente")
    private String telefone;

}

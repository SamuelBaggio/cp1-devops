package br.com.fiap.dimdim.model;

import org.springframework.hateoas.EntityModel;

import br.com.fiap.dimdim.controller.ClienteController;
import br.com.fiap.dimdim.controller.EnderecoController;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    name = "tb_endereco"
)
public class Endereco {

    @Id
    @GeneratedValue(
        generator = "seq_endereco",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "seq_endereco",
        sequenceName = "seq_endereco",
        allocationSize = 1
    )
    @Column(name = "id_endereco")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(
        name = "id_cliente",
        referencedColumnName = "id_cliente",
        foreignKey = @ForeignKey(name ="fk_endereco_cliente")
    )
    private Cliente cliente;

    @NotBlank
    @Column(name = "cep_endereco")
    private String cep;

    @NotBlank
    @Column(name = "logradouro_endereco")
    private String logradouro;

    @NotBlank
    @Column(name = "numero_endereco_empresa")
    private String numero;

    @Size(max = 255)
    @Column(name = "complemento_endereco")
    private String complemento;
    
    @NotBlank
    @Column(name = "bairro_endereco")
    private String bairro;
    
    @NotBlank
    @Column(name = "cidade_endereco")
    private String cidade;
    
    @NotBlank @Size(min = 2,  max = 2)
    @Column(name = "estado_endereco")
    private String estado;

}

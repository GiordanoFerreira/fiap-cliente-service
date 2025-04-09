package br.com.postechfiap.fiap_cliente_service.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "cliente")
@Builder
@Entity
@Table(name = Endereco.TABLE_NAME)
public class Endereco extends BaseEntity<Long>{

    public static final String TABLE_NAME = "endereco";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq_gen")
    @SequenceGenerator(name = "endereco_seq_gen", sequenceName = "endereco_id_seq", allocationSize = 1)
    private Long id;

    @Setter
    @NotBlank(message = "O logradouro é obrigatório!")
    private String logradouro;

    @Setter
    @NotBlank(message = "O numero é obrigatório!")
    @Pattern(regexp = "^(\\d+[A-Za-z\\-/]*)|S/N$", message = "Número inválido. Use dígitos/letras ou 'S/N'")
    private String numero;

    @Setter
    private String complemento;

    @Setter
    @NotBlank(message = "O bairro é obrigatório!")
    private String bairro;

    @Setter
    @NotBlank(message = "O cidade é obrigatório!")
    private String cidade;

    @Setter
    @NotBlank(message = "O estado é obrigatório!")
    private String estado;

    @Setter
    @NotBlank(message = "O CEP é obrigatório!")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
    private String cep;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O cliente é obrigatório!")
    private Cliente cliente;
}

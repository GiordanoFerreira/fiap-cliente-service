package br.com.postechfiap.fiap_cliente_service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente extends BaseEntity<Long>{

    public static final String TABLE_NAME = "cliente";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq_gen")
    @SequenceGenerator(name = "cliente_seq_gen", sequenceName = "cliente_id_seq", allocationSize = 1)
    private Long id;

    @Setter
    @NotBlank(message = "O nome é obrigatório!")
    @Column(nullable = false)
    private String nome;

    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Setter
    @NotBlank(message = "O CPF é obrigatório!")
    @Column(nullable = false, unique = true)
    private String cpf;

    @Setter
    @NotBlank(message = "O e-mail é obrigatório!")
    @Email(message = "O e-mail é inválido!")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();
}

package br.com.postechfiap.fiap_cliente_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório!") String nome,
        @Past(message = "A data de nascimento deve estar no passado!")
        @NotNull(message = "A data de nascimento é obrigatório!")
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
        @NotBlank(message = "O CPF é obrigatório!") @CPF String cpf,
        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-mail é inválido!") String email,
        List<EnderecoRequestDTO> enderecos
) {
}
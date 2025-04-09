package br.com.postechfiap.fiap_cliente_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EnderecoRequestDTO(
        @NotBlank(message = "O CEP é obrigatório!")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000") String cep,
        @NotBlank(message = "O logradouro é obrigatório!") String logradouro,
        @NotBlank(message = "O numero é obrigatório!")
        @Pattern(regexp = "^(\\d+[A-Za-z\\-/]*)|S/N$", message = "Número inválido. Use dígitos/letras ou 'S/N'") String numero,
        String complemento,
        @NotBlank(message = "O bairro é obrigatório!") String bairro,
        @NotBlank(message = "O cidade é obrigatório!") String cidade,
        @NotBlank(message = "O estado é obrigatório!") String estado
) {
}

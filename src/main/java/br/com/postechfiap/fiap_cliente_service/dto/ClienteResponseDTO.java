package br.com.postechfiap.fiap_cliente_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ClienteResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String email,
        List<EnderecoResponseDTO> enderecos
) {
}

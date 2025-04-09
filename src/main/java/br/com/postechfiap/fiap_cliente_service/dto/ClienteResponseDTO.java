package br.com.postechfiap.fiap_cliente_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        List<EnderecoResponseDTO> enderecos
) {
}

package br.com.postechfiap.fiap_cliente_service.adapters;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClienteAdapter {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        if (dto == null) return null;

        return Cliente.builder()
                .nome(dto.nome())
                .dataNascimento(dto.dataNascimento())
                .cpf(dto.cpf())
                .email(dto.email())
                .enderecos(dto.enderecos() != null ?
                        dto.enderecos().stream()
                                .map(EnderecoAdapter::toEntity)
                                .collect(Collectors.toList())
                        :new ArrayList<>()
                ).build();
    }

    public static ClienteResponseDTO toResponse(Cliente cliente) {
        if (cliente == null) return null;

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getEnderecos() != null ?
                        cliente.getEnderecos().stream()
                                .map(EnderecoAdapter::toResponse)
                                .collect(Collectors.toList())
                        :new ArrayList<>()
        );
    }
}

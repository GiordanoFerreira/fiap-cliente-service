package br.com.postechfiap.fiap_cliente_service.adapters;

import br.com.postechfiap.fiap_cliente_service.dto.EnderecoRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.EnderecoResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Endereco;

public class EnderecoAdapter {

    public static Endereco toEntity(EnderecoRequestDTO dto) {
        if (dto == null) return null;

        return Endereco.builder()
                .cep(dto.cep())
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .complemento(dto.complemento())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .build();
    }

    public static EnderecoResponseDTO toResponse(Endereco endereco) {
        if (endereco == null) return null;

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}

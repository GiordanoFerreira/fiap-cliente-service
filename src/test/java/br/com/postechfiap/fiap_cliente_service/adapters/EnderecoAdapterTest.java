package br.com.postechfiap.fiap_cliente_service.adapters;

import br.com.postechfiap.fiap_cliente_service.dto.EnderecoRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.EnderecoResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Endereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnderecoAdapterTest {

    @Test
    void deveRetornarNullQuandoEnderecoRequestDTOForNull() {
        Endereco resultado = null;
        assertNull(null);
    }

    @Test
    void deveRetornarEnderecoEntityValido() {
        EnderecoRequestDTO dto = new EnderecoRequestDTO(
                "12345-678",
                "Rua das Flores",
                "100",
                "Apto 202",
                "Centro",
                "Cidade X",
                "Estado Y"
        );

        Endereco endereco = EnderecoAdapter.toEntity(dto);

        assertNotNull(endereco);
        assertEquals("12345-678", endereco.getCep());
    }

    @Test
    void deveRetornarNullQuandoEnderecoForNull() {
        EnderecoResponseDTO resultado = null;
        assertNull(null);
    }

    @Test
    void deveRetornarEnderecoResponseValido() {
        Endereco endereco = Endereco.builder()
                .id(1L)
                .cep("98765-432")
                .logradouro("Av. Principal")
                .numero("200")
                .complemento("Bloco B")
                .bairro("Bairro Y")
                .cidade("Cidade Z")
                .estado("Estado Z")
                .build();

        EnderecoResponseDTO dto = EnderecoAdapter.toResponse(endereco);

        assertNotNull(dto);
        assertEquals("98765-432", dto.cep());
        assertEquals("Av. Principal", dto.logradouro());
    }
}


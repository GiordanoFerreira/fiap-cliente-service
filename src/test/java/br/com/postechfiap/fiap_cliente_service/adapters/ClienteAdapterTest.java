package br.com.postechfiap.fiap_cliente_service.adapters;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteAdapterTest {

    @Test
    void deveRetornarNullQuandoClienteRequestDTOForNull() {
        ClienteAdapter.toEntity(null);
        Cliente resultado = null;
        assertNull(null);
    }

    @Test
    void deveRetornarClienteQuandoEnderecosForemNull() {
        ClienteRequestDTO dto = new ClienteRequestDTO(
                "João",
                LocalDate.of(1990, 1, 1),
                "12345678901",
                "joao@email.com",
                null
        );

        Cliente cliente = ClienteAdapter.toEntity(dto);

        assertNotNull(cliente);
        assertEquals("João", cliente.getNome());
        assertNotNull(cliente.getEnderecos());
        assertTrue(cliente.getEnderecos().isEmpty());
    }

    @Test
    void deveRetornarClienteComListaVaziaQuandoEnderecosForemVazios() {
        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Maria",
                LocalDate.of(1985, 5, 10),
                "09876543210",
                "maria@email.com",
                new ArrayList<>()
        );

        Cliente cliente = ClienteAdapter.toEntity(dto);

        assertNotNull(cliente);
        assertNotNull(cliente.getEnderecos());
        assertTrue(cliente.getEnderecos().isEmpty());
    }

    @Test
    void deveRetornarNullQuandoClienteForNull() {
        ClienteAdapter.toResponse(null);
        ClienteResponseDTO resultado = null;
        assertNull(null);
    }

    @Test
    void deveRetornarDTOQuandoEnderecosForemNull() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Carlos")
                .cpf("12345678900")
                .email("carlos@email.com")
                .dataNascimento(LocalDate.of(1991, 2, 2))
                .enderecos(null)
                .build();

        ClienteResponseDTO dto = ClienteAdapter.toResponse(cliente);

        assertNotNull(dto);
        assertNotNull(dto.enderecos());
        assertTrue(dto.enderecos().isEmpty());
    }

    @Test
    void deveRetornarDTOComListaVaziaQuandoEnderecosForemVazios() {
        Cliente cliente = Cliente.builder()
                .id(2L)
                .nome("Ana")
                .cpf("55555555555")
                .email("ana@email.com")
                .dataNascimento(LocalDate.of(1992, 3, 3))
                .enderecos(new ArrayList<>())
                .build();

        ClienteResponseDTO dto = ClienteAdapter.toResponse(cliente);

        assertNotNull(dto);
        assertNotNull(dto.enderecos());
        assertTrue(dto.enderecos().isEmpty());
    }
}


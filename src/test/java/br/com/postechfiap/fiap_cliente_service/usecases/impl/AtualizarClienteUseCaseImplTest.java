package br.com.postechfiap.fiap_cliente_service.usecases.impl;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.dto.EnderecoRequestDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.ClienteNaoEncontradoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.usecase.AtualizarClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteUseCaseImplTest {

    @InjectMocks
    private AtualizarClienteUseCaseImpl atualizarClienteUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveAtualizarClienteComSucesso() {
        // Arrange - cliente existente
        Cliente clienteExistente = Cliente.builder()
                .id(1L)
                .nome("Cliente Antigo")
                .cpf("12345678900")
                .email("antigo@email.com")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .enderecos(new ArrayList<>())
                .build();

        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Cliente Atualizado",
                LocalDate.of(1985, 05, 05),
                "12345678900",
                "atualizado@email.com",
                List.of(new EnderecoRequestDTO(
                        "00000-000", "Rua Nova", "100", "Casa", "Centro", "Cidade", "SP"
                ))
        );

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ClienteResponseDTO response = atualizarClienteUseCase.executar(1L, dto);

        // Assert
        assertNotNull(response);
        assertEquals("Cliente Atualizado", response.nome());
        assertEquals("12345678900", response.cpf());
        assertEquals("atualizado@email.com", response.email());
        assertEquals(1, response.enderecos().size());

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoSeClienteNaoForEncontrado() {
        // Arrange
        ClienteRequestDTO dto = new ClienteRequestDTO(
                "Cliente", LocalDate.of(1990, 1, 1), "11111111111", "email@teste.com", List.of()
        );

        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNaoEncontradoException.class, () ->
                atualizarClienteUseCase.executar(99L, dto)
        );

        verify(clienteRepository).findById(99L);
        verify(clienteRepository, never()).save(any());
    }
}
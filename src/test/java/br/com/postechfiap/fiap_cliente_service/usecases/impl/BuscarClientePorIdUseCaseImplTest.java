package br.com.postechfiap.fiap_cliente_service.usecases.impl;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.ClienteNaoEncontradoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.usecase.BuscarClientePorIdUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdUseCaseImplTest {

    @InjectMocks
    private BuscarClientePorIdUseCaseImpl buscarClientePorIdUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveRetornarClienteQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Cliente cliente = Cliente.builder()
                .id(id)
                .nome("João da Silva")
                .cpf("12345678900")
                .build();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        ClienteResponseDTO response = buscarClientePorIdUseCase.executar(id);

        // Assert
        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("João da Silva", response.nome());
        verify(clienteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        // Arrange
        Long id = 999L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ClienteNaoEncontradoException exception = assertThrows(
                ClienteNaoEncontradoException.class,
                () -> buscarClientePorIdUseCase.executar(id)
        );

        assertEquals("Cliente com ID: 999 não encontrado.", exception.getMessage());
        verify(clienteRepository).findById(id);
    }
}
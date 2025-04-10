package br.com.postechfiap.fiap_cliente_service.usecases.impl;

import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.ClienteNaoEncontradoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.usecase.DeletarClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarClienteUseCaseImplTest {

    @InjectMocks
    private DeletarClienteUseCaseImpl deletarClienteUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveDeletarClienteComSucesso() {
        // Arrange - cliente encontrado
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Cliente Teste")
                .cpf("12345678900")
                .email("cliente@teste.com")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        deletarClienteUseCase.executar(1L);

        // Assert
        assertNotNull(cliente.getDeletedTmsp()); // O campo deletedTmsp deve ter sido setado
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).delete(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        // Arrange
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNaoEncontradoException.class, () -> deletarClienteUseCase.executar(99L));

        verify(clienteRepository).findById(99L);
        verify(clienteRepository, never()).save(any());
    }
}
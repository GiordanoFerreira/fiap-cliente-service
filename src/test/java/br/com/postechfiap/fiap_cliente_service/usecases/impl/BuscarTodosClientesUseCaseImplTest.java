package br.com.postechfiap.fiap_cliente_service.usecases.impl;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.usecase.BuscarTodosClientesUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarTodosClientesUseCaseImplTest {

    @InjectMocks
    private BuscarTodosClientesUseCaseImpl buscarTodosClientesUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveRetornarTodosOsClientesQuandoNomeNaoForInformado() {
        // Arrange
        Cliente cliente1 = Cliente.builder().id(1L).nome("Cliente Um").cpf("11111111111").build();
        Cliente cliente2 = Cliente.builder().id(2L).nome("Cliente Dois").cpf("22222222222").build();

        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));

        // Act
        List<ClienteResponseDTO> resultado = buscarTodosClientesUseCase.executar(null);

        // Assert
        assertEquals(2, resultado.size());
        verify(clienteRepository).findAll();
        verify(clienteRepository, never()).findByNomeContainingIgnoreCase(any());
    }

    @Test
    void deveRetornarClientesPorNomeParcial() {
        // Arrange
        Cliente cliente = Cliente.builder().id(1L).nome("Maria Teste").cpf("33333333333").build();

        when(clienteRepository.findByNomeContainingIgnoreCase("maria")).thenReturn(List.of(cliente));

        // Act
        List<ClienteResponseDTO> resultado = buscarTodosClientesUseCase.executar("maria");

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("Maria Teste", resultado.get(0).nome());
        verify(clienteRepository).findByNomeContainingIgnoreCase("maria");
        verify(clienteRepository, never()).findAll();
    }

    @Test
    void deveRetornarListaVaziaSeNenhumClienteEncontrado() {
        when(clienteRepository.findByNomeContainingIgnoreCase("inexistente")).thenReturn(List.of());

        List<ClienteResponseDTO> resultado = buscarTodosClientesUseCase.executar("inexistente");

        assertTrue(resultado.isEmpty());
    }
}

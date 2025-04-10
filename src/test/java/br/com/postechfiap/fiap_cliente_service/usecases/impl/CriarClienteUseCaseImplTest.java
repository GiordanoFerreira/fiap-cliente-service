package br.com.postechfiap.fiap_cliente_service.usecases.impl;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.dto.EnderecoRequestDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.CpfDuplicadoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.CriarClienteUseCase;
import br.com.postechfiap.fiap_cliente_service.usecase.CriarClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseImplTest {

    @InjectMocks
    private CriarClienteUseCaseImpl criarClienteUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveCriarClienteComSucesso() {
        var dto = new ClienteRequestDTO(
          "Cliente Novo",
          LocalDate.of(2002, 06,04),
          "10461897008",
          "teste@gmail.com",
          List.of(new EnderecoRequestDTO(
                  "12345-678", "Rua Teste", "100", "Apto 1", "Centro", "Cidade", "SP"
          ))
        );

        when(clienteRepository.findByCpf(dto.cpf())).thenReturn(Optional.empty());

        when(clienteRepository.save(any(Cliente.class))).thenAnswer( invocation -> {
            Cliente c = invocation.getArgument(0);
            return c.toBuilder().id(1L).build();
        });

        ClienteResponseDTO responseDTO = criarClienteUseCase.executar(dto);

        assertNotNull(responseDTO);
        assertEquals("Cliente Novo", responseDTO.nome());
        assertEquals("10461897008", responseDTO.cpf());

        verify(clienteRepository).findByCpf("10461897008");
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoSeCpfDuplicado() {

        var dto = new ClienteRequestDTO(
                "Duplicado",
                LocalDate.of(2002, 06,04),
                "10461897008",
                "teste@gmail.com",
                List.of()
        );

        when(clienteRepository.findByCpf(dto.cpf())).thenReturn(Optional.of(mock(Cliente.class)));

        var excecao = assertThrows(CpfDuplicadoException.class,
                () -> criarClienteUseCase.executar(dto));

        assertEquals("Já existe um Cliente com o CPF: 10461897008", excecao.getMessage());

        verify(clienteRepository).findByCpf("10461897008");
        verify(clienteRepository, never()).save(any());
    }
}

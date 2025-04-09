package br.com.postechfiap.fiap_cliente_service.usecase;

import br.com.postechfiap.fiap_cliente_service.adapters.ClienteAdapter;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.BuscarTodosClientesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarTodosClientesUseCaseImpl implements BuscarTodosClientesUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponseDTO> executar(String nome) {
        List<Cliente> clientes;

        if (nome == null || nome.isBlank()) {
            clientes = clienteRepository.findAll();
        } else {
            clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        }

        return clientes.stream()
                .map(ClienteAdapter::toResponse)
                .toList();
    }
}

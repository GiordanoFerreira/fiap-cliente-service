package br.com.postechfiap.fiap_cliente_service.usecase;

import br.com.postechfiap.fiap_cliente_service.adapters.ClienteAdapter;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.ClienteNaoEncontradoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.BuscarClientePorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClientePorIdUseCaseImpl implements BuscarClientePorIdUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO executar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return ClienteAdapter.toResponse(cliente);
    }
}

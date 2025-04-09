package br.com.postechfiap.fiap_cliente_service.usecase;

import br.com.postechfiap.fiap_cliente_service.adapters.ClienteAdapter;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.CpfDuplicadoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.CriarClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarClienteUseCaseImpl implements CriarClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO executar(ClienteRequestDTO dto) {
        Cliente cliente = ClienteAdapter.toEntity(dto);

        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new CpfDuplicadoException(cliente.getCpf());
        }

        cliente.getEnderecos().forEach(e -> e.setCliente(cliente));

        Cliente salvo = clienteRepository.save(cliente);

        return ClienteAdapter.toResponse(salvo);
    }
}

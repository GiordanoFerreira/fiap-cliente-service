package br.com.postechfiap.fiap_cliente_service.usecase;

import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.exception.cliente.ClienteNaoEncontradoException;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.DeletarClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarClienteUseCaseImpl implements DeletarClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public void executar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        cliente.delete();
        clienteRepository.delete(cliente);
    }
}

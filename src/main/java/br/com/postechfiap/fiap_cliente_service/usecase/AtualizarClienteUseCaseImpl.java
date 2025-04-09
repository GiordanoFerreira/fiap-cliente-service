package br.com.postechfiap.fiap_cliente_service.usecase;

import br.com.postechfiap.fiap_cliente_service.adapters.ClienteAdapter;
import br.com.postechfiap.fiap_cliente_service.adapters.EnderecoAdapter;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.entities.Cliente;
import br.com.postechfiap.fiap_cliente_service.interfaces.repository.ClienteRepository;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.AtualizarClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarClienteUseCaseImpl implements AtualizarClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO executar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());

        cliente.getEnderecos().clear();
        dto.enderecos().forEach(enderecoDTO -> {
            var endereco = EnderecoAdapter.toEntity(enderecoDTO);
            endereco.setCliente(cliente);
            cliente.getEnderecos().add(endereco);
        });

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return ClienteAdapter.toResponse(clienteAtualizado);
    }
}

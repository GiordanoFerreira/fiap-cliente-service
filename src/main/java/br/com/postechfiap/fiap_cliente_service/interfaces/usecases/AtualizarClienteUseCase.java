package br.com.postechfiap.fiap_cliente_service.interfaces.usecases;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;

public interface AtualizarClienteUseCase {
    ClienteResponseDTO executar(Long id, ClienteRequestDTO dto);
}

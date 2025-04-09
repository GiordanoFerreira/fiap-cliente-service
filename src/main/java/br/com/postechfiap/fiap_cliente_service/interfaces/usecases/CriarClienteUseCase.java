package br.com.postechfiap.fiap_cliente_service.interfaces.usecases;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;

public interface CriarClienteUseCase {
    ClienteResponseDTO executar(ClienteRequestDTO dto);
}

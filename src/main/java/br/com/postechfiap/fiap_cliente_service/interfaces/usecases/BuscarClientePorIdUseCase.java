package br.com.postechfiap.fiap_cliente_service.interfaces.usecases;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;

public interface BuscarClientePorIdUseCase {
    ClienteResponseDTO executar(Long id);
}

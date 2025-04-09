package br.com.postechfiap.fiap_cliente_service.interfaces.usecases;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;

import java.util.List;

public interface BuscarTodosClientesUseCase {
    List<ClienteResponseDTO> executar(String nome);
}

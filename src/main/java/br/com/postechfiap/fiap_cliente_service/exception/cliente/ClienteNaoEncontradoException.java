package br.com.postechfiap.fiap_cliente_service.exception.cliente;

import br.com.postechfiap.fiap_cliente_service.exception.ApiException;

public class ClienteNaoEncontradoException extends ApiException {

    public ClienteNaoEncontradoException(Long id) {
        super("Cliente com ID: " + id + " não encontrado.");
    }
}

package br.com.postechfiap.fiap_cliente_service.exception.cliente;

import br.com.postechfiap.fiap_cliente_service.exception.ApiException;

public class CpfDuplicadoException extends ApiException {

    public CpfDuplicadoException(String cpf) {
        super("Já existe um Cliente com o CPF: " + cpf);
    }
}

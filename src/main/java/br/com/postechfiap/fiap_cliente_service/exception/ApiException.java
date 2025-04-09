package br.com.postechfiap.fiap_cliente_service.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}

package br.com.postechfiap.fiap_cliente_service.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String httpError,
        List<String> message
) {
    public ApiErrorResponse(int status, String error, List<String> messages) {
        this(LocalDateTime.now(), status, error, messages != null ? messages : new ArrayList<>());
    }

    public ApiErrorResponse(int status, String error, String message) {
        this(LocalDateTime.now(), status, error, List.of(message));
    }
}

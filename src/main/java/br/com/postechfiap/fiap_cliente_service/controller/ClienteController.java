package br.com.postechfiap.fiap_cliente_service.controller;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.AtualizarClienteUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.BuscarTodosClientesUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.CriarClienteUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.DeletarClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarTodosClientesUseCase buscarTodosClientesUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO response = criarClienteUseCase.executar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarCliente(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(buscarTodosClientesUseCase.executar(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO dto) {
        return ResponseEntity.ok(atualizarClienteUseCase.executar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        deletarClienteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}

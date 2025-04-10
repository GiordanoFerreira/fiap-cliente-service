package br.com.postechfiap.fiap_cliente_service.controller;

import br.com.postechfiap.fiap_cliente_service.dto.ClienteRequestDTO;
import br.com.postechfiap.fiap_cliente_service.dto.ClienteResponseDTO;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.AtualizarClienteUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.BuscarTodosClientesUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.CriarClienteUseCase;
import br.com.postechfiap.fiap_cliente_service.interfaces.usecases.DeletarClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cliente", description = "API Gerenciamento de Clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarTodosClientesUseCase buscarTodosClientesUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;

    @Operation(summary = "Cadastrar Cliente", description = "Faz o cadastro de um Cliente")
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

    @Operation(summary = "Listar Clientes", description = "Retorna uma lista de Clientes filtrado por nome ou todos")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarCliente(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(buscarTodosClientesUseCase.executar(nome));
    }

    @Operation(summary = "Atualizar Cliente", description = "Atualiza os dados de um Cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO dto) {
        return ResponseEntity.ok(atualizarClienteUseCase.executar(id, dto));
    }

    @Operation(summary = "Deletar Cliente", description = "Deleta um Cliente pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        deletarClienteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}

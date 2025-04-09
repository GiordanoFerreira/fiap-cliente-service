package br.com.postechfiap.fiap_cliente_service.interfaces.repository;

import br.com.postechfiap.fiap_cliente_service.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByClienteId(Long clienteId);
}

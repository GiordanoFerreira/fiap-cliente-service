package br.com.postechfiap.fiap_cliente_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ClienteControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarErroQuandoCpfJaExiste() throws Exception {
        String json = """
            {
              "nome": "Cliente Duplicado",
              "cpf": "06350516052",
              "email": "cliente@duplicado.com",
              "dataNascimento": "1990-01-01",
              "enderecos": [
                {
                  "cep": "12345-678",
                  "logradouro": "Rua A",
                  "numero": "10",
                  "complemento": "Apto 1",
                  "bairro": "Centro",
                  "cidade": "São Paulo",
                  "estado": "SP"
                }
              ]
            }
        """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message[0]").value(containsString("Já existe um Cliente com o CPF: 06350516052")));
    }

    @Test
    void deveRetornarErroDeValidacaoQuandoCpfEhVazio() throws Exception {
        String json = """
            {
              "nome": "Cliente Sem CPF",
              "cpf": "",
              "email": "email@teste.com",
              "dataNascimento": "1995-05-05",
              "enderecos": [
                {
                  "cep": "12345-678",
                  "logradouro": "Rua Teste",
                  "numero": "100",
                  "complemento": "Apto 1",
                  "bairro": "Centro",
                  "cidade": "Cidade",
                  "estado": "SP"
                }
              ]
            }
        """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]").value(containsString("cpf")));
    }

    @Test
    void deveRetornarErroQuandoAtualizaClienteInexistente() throws Exception {
        String json = """
            {
              "nome": "Cliente Inexistente",
              "cpf": "56124471086",
              "email": "cliente@email.com",
              "dataNascimento": "1990-01-01",
              "enderecos": []
            }
        """;

        mockMvc.perform(put("/clientes/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message[0]").value(containsString("Cliente com ID: 99 não encontrado")));
    }

    @Test
    void deveRetornarErroQuandoDeletaClienteInexistente() throws Exception {
        mockMvc.perform(delete("/clientes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message[0]").value(containsString("Cliente com ID: 99 não encontrado")));
    }
}

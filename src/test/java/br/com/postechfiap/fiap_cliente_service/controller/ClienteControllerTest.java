package br.com.postechfiap.fiap_cliente_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveListarTodosClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nome").value("Cliente Teste"));
    }

    @Test
    void deveBuscarClientePorNome() throws Exception {
        mockMvc.perform(get("/clientes?nome=Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].cpf").value("06350516052"));
    }

    @Test
    void deveCriarNovoCliente() throws Exception {
        String json = """
            {
              "nome": "Novo Cliente",
              "dataNascimento": "2000-01-01",
              "cpf": "65308345082",
              "email": "novo@email.com",
              "enderecos": [
                {
                  "cep": "11111-111",
                  "logradouro": "Rua Nova",
                  "numero": "123",
                  "complemento": "Apto 101",
                  "bairro": "Bairro Novo",
                  "cidade": "Cidade",
                  "estado": "SP"
                }
              ]
            }
        """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("65308345082"))
                .andExpect(jsonPath("$.enderecos.length()").value(1));
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        String json = """
            {
              "nome": "Cliente Atualizado",
              "cpf": "65308345082",
              "email": "atualizado@email.com",
              "dataNascimento": "1980-12-12",
              "enderecos": [
                {
                  "cep": "00000-000",
                  "logradouro": "Rua Alterada",
                  "numero": "321",
                  "complemento": "Casa",
                  "bairro": "Bairro Velho",
                  "cidade": "Cidade X",
                  "estado": "RJ"
                }
              ]
            }
        """;

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Cliente Atualizado"))
                .andExpect(jsonPath("$.enderecos.length()").value(1));
    }

    @Test
    void deveDeletarCliente() throws Exception {
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
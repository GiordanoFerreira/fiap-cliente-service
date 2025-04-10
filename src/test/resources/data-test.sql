INSERT INTO cliente (nome, cpf, email, data_nascimento)
VALUES ('Cliente Teste', '06350516052', 'teste@email.com', '1990-01-01');

INSERT INTO endereco (cep, logradouro, numero, complemento, bairro, cidade, estado, cliente_id)
VALUES ('12345-678', 'Rua Teste', '100', 'Apto 1', 'Centro', 'Cidade', 'SP', 1);


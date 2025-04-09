CREATE SEQUENCE cliente_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE cliente(
    id BIGINT PRIMARY KEY DEFAULT nextval('cliente_id_seq'),
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE NOT NULL DEFAULT '2000-01-01',
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(150) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    deleted_tmsp TIMESTAMP
);

CREATE SEQUENCE endereco_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE endereco(
    id BIGINT PRIMARY KEY DEFAULT nextval('endereco_id_seq'),
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(150) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    deleted_tmsp TIMESTAMP,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
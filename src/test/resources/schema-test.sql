DROP TABLE IF EXISTS endereco;
DROP TABLE IF EXISTS cliente;
DROP SEQUENCE IF EXISTS cliente_id_seq;
DROP SEQUENCE IF EXISTS endereco_id_seq;

CREATE SEQUENCE cliente_id_seq START WITH 1 INCREMENT BY 1;
DROP TABLE IF EXISTS endereco;
DROP TABLE IF EXISTS cliente;
DROP SEQUENCE IF EXISTS cliente_id_seq;
DROP SEQUENCE IF EXISTS endereco_id_seq;

CREATE SEQUENCE cliente_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE endereco_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE cliente (
    id BIGINT DEFAULT NEXT VALUE FOR cliente_id_seq PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL,
    data_nascimento DATE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_tmsp TIMESTAMP
);

CREATE TABLE endereco (
    id BIGINT DEFAULT NEXT VALUE FOR endereco_id_seq PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(150),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_tmsp TIMESTAMP,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
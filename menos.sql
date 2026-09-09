CREATE DATABASE menos;

USE menos;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    escola_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL DEFAULT 0,
    unidade VARCHAR(20) NOT NULL,
    quantidade_minima DECIMAL(10,2) NOT NULL DEFAULT 0,
    data_validade DATE,
    FOREIGN KEY (escola_id) REFERENCES escolas(id)
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    escola_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    FOREIGN KEY (escola_id) REFERENCES escolas(id)
);

CREATE TABLE escolas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20)
);
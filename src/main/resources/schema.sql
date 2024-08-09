-- Excluir tabelas se elas existirem
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS fornecedor;

-- Criar a tabela fornecedor
CREATE TABLE fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(50),
    email VARCHAR(100),
    telefone VARCHAR(20) NOT NULL,
    localizacao VARCHAR(255)
);

-- Criar a tabela produto com a coluna fornecedor_id
CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    tamanho VARCHAR(10) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    unidades INT NOT NULL,
    preco_a_vista DECIMAL(10, 2) NOT NULL,
    preco_parcelado DECIMAL(10, 2) NOT NULL,
    fornecedor_id INT,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);

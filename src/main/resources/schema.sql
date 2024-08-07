-- Excluir tabelas se elas existirem
DROP TABLE IF EXISTS roupa;
DROP TABLE IF EXISTS fornecedor;

-- Criar a tabela fornecedor
CREATE TABLE fornecedor (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CONTATO VARCHAR(50),
    NOME VARCHAR(100)
);

-- Criar a tabela roupa sem referência ao fornecedor
CREATE TABLE roupa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(100),
    descricao VARCHAR(255),
    tamanho VARCHAR(10),
    cor VARCHAR(50),
    unidades INT,
    precoavista DECIMAL(10, 2),
    preco_parcelado DECIMAL(10, 2)
);
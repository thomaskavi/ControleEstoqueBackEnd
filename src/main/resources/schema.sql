CREATE TABLE ROUPA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contato_fornecedor VARCHAR(255),
    cor VARCHAR(50),
    descricao VARCHAR(255),
    precoavista DECIMAL(10, 2),
    preco_parcelado DECIMAL(10, 2),
    referencia VARCHAR(100),
    tamanho VARCHAR(10),
    unidades INT
);

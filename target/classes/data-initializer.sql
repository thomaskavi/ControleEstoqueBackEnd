-- Inserir dados na tabela fornecedor
INSERT INTO fornecedor (nome, contato, email, telefone, localizacao) VALUES
('José Silva', '11987654321', 'jose.silva@exemplo.com', '+55 11 98765-4321', 'Rua das Flores, 123, São Paulo, SP'),
('Maria Oliveira', '11912345678', 'maria.oliveira@exemplo.com', '+55 11 91234-5678', 'Avenida Paulista, 456, São Paulo, SP'),
('Carlos Santos', '11876543210', 'carlos.santos@exemplo.com', '+55 11 87654-3210', 'Rua dos Pinheiros, 789, São Paulo, SP');

-- Inserir dados na tabela produto
INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF001', 'Camisa Polo Masculina', 'M', 'Azul', 15, 89.90, 99.90, 1);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF002', 'Calça Jeans Feminina', 'L', 'Preto', 25, 159.90, 179.90, 2);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF003', 'Tênis Esportivo', 'S', 'Branco', 40, 299.90, 329.90, 3);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF004', 'Vestido Feminino', 'M', 'Vermelho', 20, 199.90, 229.90, 1);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF005', 'Blusa Feminina', 'P', 'Rosa', 30, 79.90, 89.90, 2);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF006', 'Bermuda Masculina', 'G', 'Cinza', 10, 99.90, 119.90, 3);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF007', 'Jaqueta Unissex', 'M', 'Preto', 12, 249.90, 279.90, 1);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF008', 'Saia Feminina', 'M', 'Azul', 18, 69.90, 79.90, 2);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF009', 'Camiseta Básica', 'G', 'Branco', 50, 49.90, 59.90, 3);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, preco_a_vista, preco_parcelado, fornecedor_id) 
VALUES ('REF010', 'Macacão Feminino', 'M', 'Verde', 8, 139.90, 159.90, 1);

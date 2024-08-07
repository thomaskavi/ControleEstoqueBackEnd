-- Inserção de dados na tabela fornecedor
INSERT INTO fornecedor (contato, nome) VALUES ('123456789', 'Fornecedor A');
INSERT INTO fornecedor (contato, nome) VALUES ('987654321', 'Fornecedor B');
INSERT INTO fornecedor (contato, nome) VALUES ('555555555', 'Fornecedor C');

-- Inserção de dados na tabela roupa
INSERT INTO roupa (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado) 
VALUES ('REF123', 'Produto 1', 'M', 'Vermelho', 10, 100.00, 110.00);

INSERT INTO roupa (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado) 
VALUES ('REF124', 'Produto 2', 'L', 'Azul', 20, 150.00, 165.00);

INSERT INTO roupa (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado) 
VALUES ('REF125', 'Produto 3', 'S', 'Verde', 30, 200.00, 220.00);
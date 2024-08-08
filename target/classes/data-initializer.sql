--comando para H2 db spring.sql.init.data-locations=classpath:data-initializer.sql



-- Inserir dados na tabela fornecedor
INSERT INTO fornecedor (nome, contato, email, telefone, localizacao) VALUES
('José Silva', '11987654321', 'jose.silva@exemplo.com', '+55 11 98765-4321', 'Rua das Flores, 123, São Paulo, SP'),
('Maria Oliveira', '11912345678', 'maria.oliveira@exemplo.com', '+55 11 91234-5678', 'Avenida Paulista, 456, São Paulo, SP'),
('Carlos Santos', '11876543210', 'carlos.santos@exemplo.com', '+55 11 87654-3210', 'Rua dos Pinheiros, 789, São Paulo, SP');

-- Inserir dados na tabela produto
INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado, fornecedor_id) 
VALUES ('REF001', 'Camisa Polo Masculina', 'M', 'Azul', 15, 89.90, 99.90, 1);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado, fornecedor_id) 
VALUES ('REF002', 'Calça Jeans Feminina', 'L', 'Preto', 25, 159.90, 179.90, 2);

INSERT INTO produto (referencia, descricao, tamanho, cor, unidades, precoavista, preco_parcelado, fornecedor_id) 
VALUES ('REF003', 'Tênis Esportivo', 'S', 'Branco', 40, 299.90, 329.90, 3);
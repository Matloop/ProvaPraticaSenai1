-- Inserir Pessoas
INSERT INTO pessoa ( nome, email) VALUES ( 'Ana Silva', 'ana.silva@empresa.com');
INSERT INTO pessoa ( nome, email) VALUES ( 'Bruno Costa', 'bruno.costa@empresa.com');
INSERT INTO pessoa ( nome, email) VALUES ( 'Carlos Dias', 'carlos.dias@empresa.com');
INSERT INTO pessoa ( nome, email) VALUES ( 'Daniela Faria', 'daniela.faria@empresa.com');
INSERT INTO pessoa ( nome, email) VALUES ( 'Eduardo Lima', 'eduardo.lima@empresa.com');

-- Inserir Funcionários (ligados às Pessoas)
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (1, 'F001', 'TI');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (2, 'F002', 'RH');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (3, 'F003', 'Marketing');

-- Inserir Projetos (ligados aos Funcionários)
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Sistema de Vendas Online', 'Desenvolvimento de uma plataforma de e-commerce completa.', 1);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Campanha de Marketing Digital Q3', 'Planejamento e execução da campanha de marketing para o terceiro trimestre.', 3);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Migração para Nuvem', 'Mover toda a infraestrutura on-premise para a AWS.', 1);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Avaliação de Desempenho', 'Implementação do novo sistema de avaliação de desempenho.', 2);
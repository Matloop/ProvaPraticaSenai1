-- Inserindo Pessoas que TAMBÉM SÃO FUNCIONÁRIOS
INSERT INTO pessoa (nome, email, TIPO_PESSOA) VALUES ('Ana Silva', 'ana.silva@empresa.com', 'FUNCIONARIO');
INSERT INTO pessoa (nome, email, TIPO_PESSOA) VALUES ('Bruno Costa', 'bruno.costa@empresa.com', 'FUNCIONARIO');
INSERT INTO pessoa (nome, email, TIPO_PESSOA) VALUES ('Carlos Dias', 'carlos.dias@empresa.com', 'FUNCIONARIO');

-- Inserindo Pessoas que NÃO SÃO FUNCIONÁRIOS
INSERT INTO pessoa (nome, email, TIPO_PESSOA) VALUES ('Daniela Faria', 'daniela.faria@empresa.com', 'Pessoa');
INSERT INTO pessoa (nome, email, TIPO_PESSOA) VALUES ('Eduardo Lima', 'eduardo.lima@empresa.com', 'Pessoa');

-- Inserir os dados específicos dos Funcionários na tabela 'funcionario'
-- Os IDs (1, 2, 3) correspondem às pessoas inseridas como 'FUNCIONARIO'
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (1, 'F001', 'TI');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (2, 'F002', 'RH');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (3, 'F003', 'Marketing');

-- Inserir Projetos (ligados aos Funcionários)
-- Esta parte já estava correta e não precisa de alterações.
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Sistema de Vendas Online', 'Desenvolvimento de uma plataforma de e-commerce completa.', 1);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Campanha de Marketing Digital Q3', 'Planejamento e execução da campanha de marketing para o terceiro trimestre.', 3);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Migração para Nuvem', 'Mover toda a infraestrutura on-premise para a AWS.', 1);
INSERT INTO projeto (nome, descricao, id_funcionario) VALUES ('Avaliação de Desempenho', 'Implementação do novo sistema de avaliação de desempenho.', 2);
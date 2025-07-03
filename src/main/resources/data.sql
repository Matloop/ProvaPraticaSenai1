-- Inserindo Pessoas que TAMBÉM SÃO FUNCIONÁRIOS
-- É uma boa prática incluir o ID aqui para ter controle se o ddl-auto for create
INSERT INTO pessoa (id, nome, email, TIPO_PESSOA) VALUES (1, 'Ana Silva', 'ana.silva@empresa.com', 'FUNCIONARIO');
INSERT INTO pessoa (id, nome, email, TIPO_PESSOA) VALUES (2, 'Bruno Costa', 'bruno.costa@empresa.com', 'FUNCIONARIO');
INSERT INTO pessoa (id, nome, email, TIPO_PESSOA) VALUES (3, 'Carlos Dias', 'carlos.dias@empresa.com', 'FUNCIONARIO');

-- Inserindo Pessoas que NÃO SÃO FUNCIONÁRIOS
INSERT INTO pessoa (id, nome, email, TIPO_PESSOA) VALUES (4, 'Daniela Faria', 'daniela.faria@empresa.com', 'Pessoa');
INSERT INTO pessoa (id, nome, email, TIPO_PESSOA) VALUES (5, 'Eduardo Lima', 'eduardo.lima@empresa.com', 'Pessoa');

-- Inserir os dados específicos dos Funcionários na tabela 'funcionario'
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (1, 'F001', 'TI');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (2, 'F002', 'RH');
INSERT INTO funcionario (pessoa_id, matricula, departamento) VALUES (3, 'F003', 'Marketing');

-- Inserir Projetos (ligados aos Funcionários)
INSERT INTO projeto (id, nome, descricao, id_funcionario) VALUES (1, 'Sistema de Vendas Online', 'Desenvolvimento de uma plataforma de e-commerce completa.', 1);
INSERT INTO projeto (id, nome, descricao, id_funcionario) VALUES (2, 'Campanha de Marketing Digital Q3', 'Planejamento e execução da campanha de marketing para o terceiro trimestre.', 3);
INSERT INTO projeto (id, nome, descricao, id_funcionario) VALUES (3, 'Migração para Nuvem', 'Mover toda a infraestrutura on-premise para a AWS.', 1);
INSERT INTO projeto (id, nome, descricao, id_funcionario) VALUES (4, 'Avaliação de Desempenho', 'Implementação do novo sistema de avaliação de desempenho.', 2);
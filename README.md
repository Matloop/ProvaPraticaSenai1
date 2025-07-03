Funcionalidades Principais
Com o HR-Flow, você tem o controle total sobre:

Cadastro de Pessoas: Gerencie uma base de dados completa de todos os indivíduos, com validação de email e garantia de unicidade.

Promoção a Funcionário: Transforme uma pessoa comum em um funcionário com um clique, atribuindo matrícula única e departamento.

Gestão de Funcionários: Visualize, edite e organize todos os seus funcionários, com validações robustas.

Criação e Alocação de Projetos: Defina novos projetos e vincule-os diretamente a um funcionário responsável, garantindo clareza e responsabilidade.

Regras de Negócio Intuitivas: O sistema impede ações que comprometeriam a integridade dos dados (por exemplo, excluir um funcionário com projetos ativos).

Feedback Instantâneo: Mensagens claras de sucesso ou erro para uma experiência de usuário fluida.

🛠️ Tecnologias Utilizadas
Este projeto foi construído com ferramentas e frameworks modernos para garantir robustez e escalabilidade:

Spring Boot: O coração da aplicação, provendo um ambiente rápido e fácil para o desenvolvimento de aplicações Java.

Spring Data JPA: Simplifica a interação com o banco de dados, tornando o acesso a dados mais intuitivo.

Hibernate: Implementação de JPA para mapeamento objeto-relacional.

Lombok: Reduz a verbosidade do código Java, gerando getters, setters e construtores automaticamente.

HTML/Thymeleaf: Para a construção das interfaces de usuário dinâmicas e amigáveis.

Maven: Ferramenta de automação de build e gerenciamento de dependências.

Banco de Dados H2 (em memória): Perfeito para desenvolvimento e testes, garantindo um ambiente rápido e fácil de configurar.

🚀 Como Rodar Este Projeto na Sua Máquina Local
Preparar o HR-Flow para rodar na sua máquina é um processo simples. Siga estes passos e esteja pronto para gerenciar em minutos!

Pré-requisitos Essenciais:

Java Development Kit (JDK) 17 ou versão superior instalada.

Apache Maven instalado e configurado nas suas variáveis de ambiente.

Um editor de código ou IDE de sua preferência (recomendamos IntelliJ IDEA Community ou VS Code).

Clone o Repositório:
Abra seu terminal favorito (Git Bash, CMD, PowerShell, Terminal do Linux/macOS) e execute o comando abaixo para obter o código-fonte:

Bash

git clone <URL_DO_SEU_REPOSITORIO>
# Exemplo: git clone https://github.com/SeuUsuario/provapratica1.git
cd provapratica1
🚨 Não esqueça: Substitua <URL_DO_SEU_REPOSITORIO> pela URL real do seu repositório GitHub!

Habilite o Processamento de Anotações do Lombok na sua IDE:
O Lombok usa o processamento de anotações para gerar código automaticamente. Para que sua IDE reconheça os métodos gerados e evite erros de compilação, você precisa habilitar essa funcionalidade:

IntelliJ IDEA:

Vá em File > Settings (ou IntelliJ IDEA > Preferences no macOS).

No painel esquerdo, navegue até Build, Execution, Deployment > Compiler > Annotation Processors.

Certifique-se de que a opção "Enable annotation processing" esteja marcada.

Clique em Apply e OK.

Se ainda houver problemas, instale o plugin do Lombok: vá em Plugins no menu de configurações, procure por "Lombok Plugin" e instale-o. Reinicie a IDE após a instalação.

Eclipse:

Primeiro, você precisa instalar o Lombok como um plugin. Baixe o lombok.jar (geralmente ele vem como uma dependência Maven, mas para a instalação no Eclipse, você precisa do JAR executável):

Execute o JAR baixado: java -jar lombok.jar. Uma janela de instalação aparecerá.

Clique em "Specify location..." e selecione o diretório de instalação do seu Eclipse.

Clique em "Install/Update".

Após a instalação, reinicie o Eclipse.

No seu projeto, vá em Project > Properties.

No painel esquerdo, navegue até Java Compiler > Annotation Processing.

Certifique-se de que "Enable annotation processing" esteja marcado.

No submenu Annotation Processing, vá para Factory Path.

Marque "Enable project specific settings".

Clique em Add JARs... e adicione o lombok.jar do seu projeto (ele estará no seu repositório Maven local, geralmente em .m2/repository/org/projectlombok/lombok/<version>/lombok-<version>.jar).

Clique em Apply e OK.

Construa o Projeto:
Dentro do diretório provapratica1 (onde está o arquivo pom.xml), execute o comando Maven para baixar as dependências e compilar o projeto:

Bash

mvn clean install
Isso garantirá que todas as dependências estejam no lugar e o projeto esteja pronto para ser executado.

Inicie a Aplicação:
Agora, você pode iniciar o servidor Spring Boot. Escolha uma das opções:

Via Linha de Comando (Maven):

Bash

mvn spring-boot:run
Via IDE (Recomendado para Desenvolvimento):
Abra o projeto na sua IDE. Localize a classe principal da aplicação (geralmente src/main/java/com/example/provapratica1/ProvaPratica1Application.java) e execute o método main.

Acesse o HR-Flow no Seu Navegador!
Assim que a aplicação for iniciada (você verá logs no terminal indicando que o servidor está rodando na porta 8080), abra seu navegador web e navegue para:

http://localhost:8080
Pronto! Você estará na página inicial do HR-Flow, pronto para explorar todas as funcionalidades de gerenciamento.

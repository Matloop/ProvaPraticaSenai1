# 🚀 HR-Flow: Sistema Inteligente de Gerenciamento de Talentos e Projetos 📊

Bem-vindo ao **HR-Flow**, sua solução moderna e eficiente para o gerenciamento de recursos humanos e alocação de projetos! Este projeto Spring Boot foi cuidadosamente desenvolvido para simplificar a administração de pessoas, a promoção de colaboradores a funcionários e a vinculação estratégica de projetos à sua equipe.

**Diga adeus à complicação e olá à organização!**

## ✨ Funcionalidades Principais

Com o HR-Flow, você tem o controle total sobre:

*   **Cadastro de Pessoas:** Gerencie uma base de dados completa de todos os indivíduos, com validação de email e garantia de unicidade.
*   **Promoção a Funcionário:** Transforme uma pessoa comum em um funcionário com um clique, atribuindo matrícula única e departamento.
*   **Gestão de Funcionários:** Visualize, edite e organize todos os seus funcionários, com validações robustas.
*   **Criação e Alocação de Projetos:** Defina novos projetos e vincule-os diretamente a um funcionário responsável, garantindo clareza e responsabilidade.
*   **Regras de Negócio Intuitivas:** O sistema impede ações que comprometeriam a integridade dos dados (por exemplo, excluir um funcionário com projetos ativos).
*   **Feedback Instantâneo:** Mensagens claras de sucesso ou erro para uma experiência de usuário fluida.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com ferramentas e frameworks modernos para garantir robustez e escalabilidade:

*   **Spring Boot:** O coração da aplicação, provendo um ambiente rápido e fácil para o desenvolvimento de aplicações Java.
*   **Spring Data JPA:** Simplifica a interação com o banco de dados, tornando o acesso a dados mais intuitivo.
*   **Hibernate:** Implementação de JPA para mapeamento objeto-relacional.
*   **Lombok:** Reduz a verbosidade do código Java, gerando getters, setters e construtores automaticamente.
*   **HTML/Thymeleaf:** Para a construção das interfaces de usuário dinâmicas e amigáveis.
*   **Maven:** Ferramenta de automação de build e gerenciamento de dependências.
*   **Banco de Dados H2 (em memória):** Perfeito para desenvolvimento e testes, garantindo um ambiente rápido e fácil de configurar.

## 🚀 Como Rodar Este Projeto na Sua Máquina Local

Preparar o HR-Flow para rodar na sua máquina é um processo simples. Siga estes passos e esteja pronto para gerenciar em minutos!

1.  **Pré-requisitos Essenciais:**
    *   [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/downloads/) ou versão superior instalada.
    *   [Apache Maven](https://maven.apache.org/download.cgi) instalado e configurado nas suas variáveis de ambiente.
    *   Um editor de código ou IDE de sua preferência (recomendamos [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) ou [VS Code](https://code.visualstudio.com/)).

2.  **⚙️ Configuração do Lombok na sua IDE (Passo Crucial!):**
    O HR-Flow utiliza a biblioteca **Lombok** para reduzir o código repetitivo (como getters e setters). Para que sua IDE reconheça o código gerado automaticamente e não aponte falsos erros de compilação, siga as instruções abaixo para a sua ferramenta:

    *   **Para usuários do IntelliJ IDEA:**
        1.  **Instale o Plugin Lombok:** Vá em `File` -> `Settings` -> `Plugins`. Na aba `Marketplace`, procure por "Lombok" e clique em **Install**.
        2.  **Habilite o "Annotation Processing":** Vá em `File` -> `Settings` -> `Build, Execution, Deployment` -> `Compiler` -> `Annotation Processors`. Marque a caixa **Enable annotation processing**.
        3.  Reinicie a IDE para que as alterações tenham efeito.

    *   **Para usuários do Eclipse IDE:**
        1.  **Encontre o `lombok.jar`:** Após executar `mvn clean install` (passo 4), o arquivo `lombok.jar` estará no seu repositório local do Maven (geralmente em `~/.m2/repository/org/projectlombok/lombok/...`).
        2.  **Execute o Instalador:** Dê um duplo clique no arquivo `lombok.jar` ou execute-o via terminal com o comando `java -jar lombok.jar`.
        3.  **Aponte para a sua Instalação:** Um instalador gráfico será aberto. Ele deve detectar sua instalação do Eclipse automaticamente. Caso não detecte, clique em `Specify location...` e aponte para o arquivo `eclipse.exe` (ou executável correspondente) dentro da pasta de instalação do seu Eclipse.
        4.  Clique em **Install / Update**.
        5.  Reinicie o Eclipse e confirme que o "Annotation Processing" está habilitado nas configurações do projeto (`Project` > `Properties` > `Java Compiler` > `Annotation Processing`).

3.  **Clone o Repositório:**
    Abra seu terminal favorito (Git Bash, CMD, PowerShell, Terminal do Linux/macOS) e execute o comando abaixo para obter o código-fonte:

    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    # Exemplo: git clone https://github.com/SeuUsuario/provapratica1.git
    cd provapratica1
    ```
    🚨 **Não esqueça:** Substitua `<URL_DO_SEU_REPOSITORIO>` pela URL real do seu repositório GitHub!

4.  **Construa o Projeto:**
    Dentro do diretório `provapratica1` (onde está o arquivo `pom.xml`), execute o comando Maven para baixar as dependências e compilar o projeto:

    ```bash
    mvn clean install
    ```
    Isso garantirá que todas as dependências estejam no lugar e o projeto esteja pronto para ser executado.

5.  **Inicie a Aplicação:**
    Agora, você pode iniciar o servidor Spring Boot. Escolha uma das opções:

    *   **Via Linha de Comando (Maven):**
        ```bash
        mvn spring-boot:run
        ```

    *   **Via IDE (Recomendado para Desenvolvimento):**
        Abra o projeto na sua IDE. Localize a classe principal da aplicação (geralmente `src/main/java/com/example/provapratica1/ProvaPratica1Application.java`) e execute o método `main`.

6.  **Acesse o HR-Flow no Seu Navegador!**
    Assim que a aplicação for iniciada (você verá logs no terminal indicando que o servidor está rodando na porta 8080), abra seu navegador web e navegue para:

    ```
    http://localhost:8080
    ```

    **Pronto!** Você estará na página inicial do HR-Flow, pronto para explorar todas as funcionalidades de gerenciamento.


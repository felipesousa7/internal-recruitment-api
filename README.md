# Sistema de Recrutamento Interno - Backend
Esta é uma aplicação web criada para facilitar o processo de recrutamento interno para os colaboradores da empresa.

### Tecnologias Utilizadas
* Java 17
* Spring Boot 3.2.5
* PostgreSQL

### Funcionalidades
* Autenticação e autorização
* Cadastro e gerenciamento de vagas
* Interação com o frontend para comunicação de dados

### Como Executar
* Certifique-se de ter o JDK e o Maven instalados.
* Clone este repositório.
* Configure o arquivo application.properties com as credenciais do banco de dados.
* Abra o terminal na pasta do projeto.
* Execute mvn clean install para compilar o projeto.
* Execute mvn spring-boot:run para iniciar o servidor.
* Clone e execute o projeto [`Front-end`](https://github.com/felipesousa7/internal-recruitment-front)

### Estrutura do Projeto
* domain: Representa entidades principais da aplicação.
* repositories: Repositórios para acesso aos dados.
* DTOs: Transferem dados entre Controllers e Repositories.
* controllers: Controladores REST para endpoints da API.

### Endpoints Disponíveis
* POST /auth/login: Autentica usuário e retorna token JWT.
* POST /auth/register: Registra novo usuário e retorna token JWT.
* POST /jobs/create: Cria nova vaga de emprego.
* PUT /jobs/update/{id}: Atualiza vaga de emprego existente.
* DELETE /jobs/delete/{id}: Remove vaga de emprego.
* GET /jobs/list: Lista todas as vagas de emprego.
* GET /jobs/find/{id}: Retorna detalhes de uma vaga de emprego.
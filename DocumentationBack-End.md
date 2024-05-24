# Documentação da API - Aquamate

## Visão Geral do Projeto

### Introdução
Aquamate é uma API que gerencia dados de usuários, metas de consumo e registros de consumo de água. A API permite criar, ler, atualizar e excluir informações relacionadas aos usuários e seus consumos de água.
<br>
### Arquitetura do Sistema
A API é construída usando o framework Spring Boot. A estrutura do projeto segue a arquitetura MVC (Model-View-Controller), organizada em pacotes como `controller`, `dto`, `model`, `repository` e `service`.
<br>
## Configuração Inicial

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- Banco de Dados MySQL.
### Instalação
1. Clone o repositório:
   ```
   git clone https://github.com/seu-usuario/aquamate.git
   cd aquamate

2. Instale as dependências:

```
  mvn install
```
## Configuração
### Configure as variáveis de ambiente no arquivo application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/aquamate
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```
## Estrutura do Projeto
```
Aquamate/
├── config/
├── controller/
│   ├── DadosUsuarioController.java
│   ├── MetaController.java
│   ├── RegistroConsumoController.java
│   └── UsuarioController.java
├── dto/
│   ├── DadosUsuarioDTO.java
│   ├── MetaAutoDTO.java
│   ├── MetaManualDTO.java
│   ├── RegistroConsumoDTO.java
│   └── UsuarioDTO.java
├── model/
│   ├── DadosUsuario.java
│   ├── MetaAuto.java
│   ├── MetaManual.java
│   ├── RegistroConsumo.java
│   ├── Usuario.java
├── repository/
│   ├── DadosUsuarioRepository.java
│   ├── MetaAutoRepository.java
│   ├── MetaManualRepository.java
│   ├── RegistroConsumoRepository.java
│   ├── UsuarioRepository.java
├── service/
│   ├── CadastroService.java
│   ├── IConverteDados.java
│   ├── MetaService.java
│   ├── RegistroConsumoService.java
│   └── UsuarioService.java
└── AquamateApplication.java
```

## Controllers

## UsuarioController.java

### Base URL: /usuario

### Endpoints:


1. GET /id-usuario

* Descrição: Obtém o ID do usuário pelo email.
* Parâmetros de Consulta:
  + email (String) - O email do usuário.
* Resposta de Sucesso: 200 OK com o ID do usuário.
* Resposta de Erro: 400 Bad Request se o email for inválido.
<br>

2. POST /registro

* Descrição: Registra um novo usuário.
* Corpo da Requisição:
```
{
    "email": "string",
    "senha": "string"
}
```

* Resposta de Sucesso: 200 OK com a mensagem de sucesso.
* Resposta de Erro: 400 Bad Request se os dados forem inválidos.
<br>

3. POST /login

* Descrição: Realiza o login do usuário.
* Corpo da Requisição:
```
{
    "email": "string",
    "senha": "string"
}
```
* Resposta de Sucesso: 200 OK com os dados do usuário.
* Resposta de Erro: 401 Unauthorized se as credenciais forem inválidas.
<br>

4. POST /logout

* Descrição: Realiza o logout do usuário.
* Resposta de Sucesso: 200 OK com a mensagem de sucesso.
<br>

## RegistroConsumoController.java

### Base URL: /consumo

### Endpoints:


1. POST /registrar

* Descrição: Registra um novo consumo.
* Parâmetros de Consulta:
  + id_dadosUsuario (Long) - O ID dos dados do usuário.
* Corpo da Requisição:
```
{
    "data": "string",
    "quantidade": "double"
}
```
* Resposta de Sucesso: 200 OK com os dados do consumo registrado.
<br>


2. GET /resgatar

* Descrição: Obtém o registro de consumo de um usuário específico.
* Parâmetros de Consulta:
  + id_dadosUsuario (Long) - O ID dos dados do usuário.
* Resposta de Sucesso: 200 OK com os dados do consumo.
* Resposta de Erro: 404 Not Found se não houver registro de consumo.
<br>


3. PUT /atualizar

* Descrição: Atualiza o registro de consumo de um usuário específico.
* Parâmetros de Consulta:
  + id_dadosUsuario (Long) - O ID dos dados do usuário.
* Corpo da Requisição:
```
{
    "data": "string",
    "quantidade": "double"
}
```
* Resposta de Sucesso: 200 OK com os dados do consumo atualizado.


## MetaController.java

### Base URL: /meta

### Endpoints:


1. GET /all

* Descrição: Obtém todas as metas manuais.
* Resposta de Sucesso: 200 OK com a lista de metas manuais.
<br>


2. GET /auto/{id_dadosUsuario}

* Descrição: Obtém a meta automática de um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Resposta de Sucesso: 200 OK com a meta automática.
* Resposta de Erro: 404 Not Found se a meta não for encontrada.
<br>


3. GET /manual/{id_dadosUsuario}

* Descrição: Obtém a meta manual de um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Resposta de Sucesso: 200 OK com a meta manual.
* Resposta de Erro: 404 Not Found se a meta não for encontrada.
<br>


4. POST /auto/{id_dadosUsuario}

* Descrição: Cria uma nova meta automática para um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Corpo da Requisição:
```
{
    "meta": "double",
    "dataInicio": "string",
    "dataFim": "string"
}
```
* Resposta de Sucesso: 200 OK com a meta automática criada.
<br>


5. POST /manual/{id_dadosUsuario}

* Descrição: Cria uma nova meta manual para um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Corpo da Requisição:
```
{
    "meta": "double",
    "dataInicio": "string",
    "dataFim": "string"
}
```
* Resposta de Sucesso: 200 OK com a meta manual criada.
<br>


6. PUT /auto/{id_dadosUsuario}

* Descrição: Atualiza a meta automática de um usuário específico.
* Parâmetros de Caminho:
+ id_dadosUsuario (Long) - O ID do usuário.
* Resposta de Sucesso: 200 OK com a meta automática atualizada.
<br>


7. PUT /manual/{id_dadosUsuario}

* Descrição: Atualiza a meta manual de um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Corpo da Requisição:
```
{
    "meta": "double",
    "dataInicio": "string",
    "dataFim": "string"
}
```
* Resposta de Sucesso: 200 OK com a meta manual atualizada.
<br>


8. DELETE /auto/{id_dadosUsuario}

* Descrição: Exclui a meta automática de um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Resposta de Sucesso: 204 No Content se a meta for excluída.
* Resposta de Erro: 404 Not Found se a meta não for encontrada.
<br>


9. DELETE /manual/{id_dadosUsuario}

* Descrição: Exclui a meta manual de um usuário específico.
* Parâmetros de Caminho:
  + id_dadosUsuario (Long) - O ID do usuário.
* Resposta de Sucesso: 204 No Content se a meta for excluída.
* Resposta de Erro: 404 Not Found se a meta não for encontrada.
<br>


## DadosUsuarioController.java

### Base URL: /dadosUsuario

### Endpoints:


1. GET /

* Descrição: Obtém os dados do usuário pelo ID.
* Parâmetros de Consulta:
  + id_usuario (Long) - O ID do usuário.
* Resposta de Sucesso: 200 OK com os dados do usuário.
* Resposta de Erro: 404 Not Found se os dados não forem encontrados.
<br>

2. GET /usuario

* Descrição: Obtém todos os usuários.
* Resposta de Sucesso: 200 OK com a lista de usuários.
<br>


3. POST /usuario

* Descrição: Cadastra um novo usuário.
* Corpo da Requisição:
```
{
    "nome": "string",
    "email": "string",
    "senha": "string"
}
```
* Resposta de Sucesso: 201 Created com os dados do usuário cadastrado.
<br>


4. POST /post

* Descrição: Cadastra novos dados de usuário.
* Parâmetros de Consulta:
  + id_usuario (Long) - O ID do usuário.
* Corpo da Requisição:
```
{
    "peso": "double",
    "altura": "double",
    "idade": "int",
    "genero": "string"
}
```
* Resposta de Sucesso: 201 Created com os dados do usuário cadastrados.
<br>


5. PUT /atualizar

* Descrição: Atualiza os dados de um usuário específico.
* Parâmetros de Consulta:
  + id_usuario (Long) - O ID do usuário.
* Corpo da Requisição:
```
{
    "peso": "double",
    "altura": "double",
    "idade": "int",
    "genero": "string"
}
```
* Resposta de Sucesso: 200 OK com os dados do usuário atualizados.

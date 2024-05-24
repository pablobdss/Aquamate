# Configurando Aplicação Spring Boot no EC2 com Banco de Dados RDS na AWS

## Introdução

Este guia explica como configurar e implantar uma aplicação Spring Boot em uma instância EC2 da AWS, conectando-a a um banco de dados MySQL RDS. Inclui etapas para a configuração do banco de dados, configuração do `application.properties` para conectar ao RDS e instruções para transferir e executar o `.jar` da aplicação no EC2.

## Pré-requisitos

- Conta AWS ativa
- AWS CLI configurado
- Instância EC2 configurada
- Banco de dados RDS MySQL configurado
- Projeto Spring Boot configurado

## Passo 1: Configuração da VPC

1. **Criar VPC:**
   - Acesse o console da AWS e vá para o serviço VPC.
   - Clique em "Create VPC".
   - Defina um nome e um intervalo de CIDR, por exemplo, `10.0.0.0/16`.
   - Clique em "Create".

2. **Criar Subnets:**
   - Crie pelo menos duas subnets (pública e privada) dentro da VPC criada.
   - Especifique intervalos de CIDR menores, como `10.0.1.0/24` para a pública e `10.0.2.0/24` para a privada.

3. **Configurar Internet Gateway:**
   - Crie um Internet Gateway e anexe-o à sua VPC.
   - Atualize a tabela de rotas da subnet pública para apontar para o Internet Gateway, permitindo acesso à internet.

4. **Criar Tabelas de Rotas:**
   - Crie uma tabela de rotas para a subnet privada e uma para a pública.
   - Adicione uma rota na tabela de rotas pública para o Internet Gateway.

## Passo 2: Configurar Grupos de Segurança

1. **Grupo de Segurança para EC2:**
   - Crie um grupo de segurança para a instância EC2.
   - Adicione regras para permitir tráfego de entrada nas portas 22 (SSH) e 8080 (HTTP).

2. **Grupo de Segurança para RDS:**
   - Crie um grupo de segurança para o RDS.
   - Adicione uma regra para permitir tráfego de entrada na porta 3306 (MySQL) vindo do grupo de segurança da instância EC2.

## Passo 3: Criar e Configurar a Instância EC2

1. **Lançar Instância EC2:**
   - No console EC2, clique em "Launch Instance".
   - Selecione uma AMI (Amazon Machine Image), como Amazon Linux 2.
   - Escolha o tipo de instância (ex.: t2.micro para teste).
   - Configure a instância na VPC criada, associando-a à subnet pública.
   - Anexe o grupo de segurança criado para a instância EC2.
   - Adicione um par de chaves para SSH e baixe o arquivo `.pem`.

2. **Conectar à Instância EC2:**
   - Usando SSH, conecte-se à sua instância EC2:
     ```sh
     ssh -i "path/to/your-key.pem" ec2-user@your-ec2-public-dns
     ```

## Passo 4: Configurar o Banco de Dados RDS

1. **Criação do Banco de Dados RDS:**
   - Acesse o console do AWS RDS.
   - Clique em "Create database".
   - Selecione "MySQL" como mecanismo do banco de dados.
   - Configure as especificações do banco de dados conforme necessário.
   - Associe o banco de dados ao grupo de segurança criado para o RDS.
   - Anote o endpoint do banco de dados, nome de usuário e senha para usar na configuração da aplicação.

## Passo 5: Configurar a Aplicação Spring Boot

1. **Configuração do `application.properties`:**
   Atualize o arquivo `application.properties` para usar as variáveis de ambiente para a configuração do banco de dados. Isso facilita a configuração e segurança, evitando hardcoding de credenciais.

   ```properties
   spring.datasource.url=jdbc:mysql://${DB_URL}:3306/AquaMate?createDatabaseIfNotExist=truespring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
## Passo 6: Definir Variáveis de Ambiente no EC2

1. **Definir Variáveis de Ambiente:**
   No terminal SSH conectado à sua instância EC2, defina as variáveis de ambiente:

   ```sh
   export DB_URL=jdbc:mysql://your-rds-endpoint:3306/your-database-name
   export DB_USERNAME=your-db-username
   export DB_PASSWORD=your-db-password

## Passo 7: Transferir e Executar o .jar da Aplicação no EC2
1. **Construir o .jar da Aplicação:**
No seu ambiente local, construa o arquivo .jar da sua aplicação:

```sh
mvn clean package
```
Transferir o .jar para o EC2:
Use o scp para transferir o arquivo .jar para a instância EC2:

```sh
scp -i "path/to/your-key.pem" target/your-app-name.jar ec2-user@your-ec2-public-dns:/home/ec2-user
```
Executar a Aplicação no EC2:
Conecte-se à sua instância EC2 via SSH e execute a aplicação:

```sh
java -jar /home/ec2-user/your-app-name.jar
```
## Passo 8: Testar a Conexão e a Aplicação
1. **Testar a Aplicação**:
Acesse http://your-ec2-public-dns:8080 no seu navegador para verificar se a aplicação está rodando corretamente e conectada ao banco de dados RDS.

## Conclusão
Seguindo esses passos, você conseguirá configurar e executar sua aplicação Spring Boot em uma instância EC2, conectando-a a um banco de dados MySQL RDS. Esta abordagem permite uma implantação escalável e gerenciável de sua aplicação na nuvem AWS.

## Referências
[Documentação da AWS VPC](https://docs.aws.amazon.com/vpc/index.html)
[Documentação da AWS EC2](https://docs.aws.amazon.com/ec2/index.html)
[Documentação da AWS RDS](https://docs.aws.amazon.com/rds/index.html)
[Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)




  

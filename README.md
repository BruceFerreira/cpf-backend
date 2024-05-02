# Documentação do Backend

Este é o README para o projeto de backend da aplicação, desenvolvido em Spring Boot com Java 17.

## Descrição Geral

O backend deste projeto foi desenvolvido para fornecer funcionalidades de cadastro de pessoa física, validação de CPF e geração de relatórios CSV com informações de clientes. Ele utiliza a arquitetura de mensageria RabbitMQ para comunicação assíncrona entre componentes.

## Tecnologias Utilizadas

- Spring Boot: Framework utilizado para desenvolvimento do backend.
- Java 17: Linguagem de programação utilizada para implementação do backend.
- RabbitMQ: Sistema de mensageria utilizado para comunicação assíncrona entre os componentes.
- Flyway: Utilizado para controle de migrações do banco de dados.
- PostgreSQL: Banco de dados utilizado.
- SLF4J: Framework de logging utilizado para geração de logs precisos.

## Arquitetura

O backend é construído utilizando uma arquitetura baseada em mensageria RabbitMQ, com dois exchanges e três filas:

- **Exchanges**:
  - `cpf-exchange`: Utilizado para a comunicação relacionada à validação de CPF.
  - `report-exchange`: Utilizado para a comunicação relacionada à geração de relatórios CSV.

- **Filas**:
  - `new-cpf-queue`: Fila para receber novos CPFs a serem validados.
  - `duplicated-cpf-queue`: Fila para receber CPFs duplicados após a validação.
  - `generate-report-queue`: Fila para solicitar a geração de relatórios CSV com informações de clientes.

## Geração de Relatórios CSV

Um arquivo CSV chamado `cpf.csv` é gerado automaticamente na raiz do projeto toda vez que um cliente novo é cadastrado de forma assíncrona. Isso é feito em resposta à mensagem enviada para a fila `generate-report-queue`. O arquivo CSV conterá informações dos clientes cadastrados.

## Arquivo de Log de Auditoria

Um arquivo de log de auditoria é gerado na raiz do projeto com o nome `cpf.log`. O SLF4J foi utilizado para gerar logs precisos, fornecendo uma trilha de auditoria detalhada das operações realizadas no sistema.

## Instruções de Uso

1. Clone o repositório do backend.
2. Certifique-se de ter o Docker instalado e configurado corretamente.
3. No diretório do projeto backend, execute o seguinte comando para gerar a imagem do backend:
   ```bash
   docker build -t cpf-backend .
4. Na raiz do projeto, execute `docker-compose up` para subir a aplicação backend, banco de dados e RabbitMQ.

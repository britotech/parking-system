# API REST para controlar cobranças de um estacionamento

Este é uma api para controlar um fluxo de um estacionamento.

É um projeto Java com maven utilizando `String Boot` para configuração, `Spring Data` com `PostgresSQL` para persistência, `Spring Security` para autorização e autenticação no padrão `JWT`.

Foi utilizado o `Flyway` para gerir as migrações no banco de dados e `SpringDoc` para documentação e acesso dos endpoints para testes.
Utilizado também `Junit` e `RestAssured` para realização de testes de api.

## Requisitos

JDK 17 ou superior.

PostgresSQL com os bancos de dados `parking` e `parking-test` criados (A criação de tabelas e registros é realizada através de migrações pelo `Flyway`).

## Execução
O projeto pode ser rodado em qualquer ide para desenvolvimento java ou através do maven.

Para verificar se o projeto está rodando você pode fazer uma requisção para o endpoint  `/status ` o qual deve retornar `It's work` no corpo da mensagem.
Para realizar testes na api você pode acessar a documentação pelo endpoint `/swagger-ui/index.html`.

O sistema possui dois usuários default, com os usernames  `admin ` e  `user ` e os dois com password  `123`.

Para acessar as demais urls é necessário informar um token de autenticação, para obter o token basta realizar uma requisção no endpoint `/auth/login` informando
um usuário válido no corpo da requisição.

<div align="center">
  <img src="https://user-images.githubusercontent.com/30123041/198751272-91e4d7a7-10d5-4b77-a132-c3929378b4e6.png"/>
</div>  

<div align="center">
  <img src="https://user-images.githubusercontent.com/30123041/198751299-10965c91-c371-4755-aea7-4c3834b5b737.png"/>
</div>  

Será retornado o token de autorização, basta incluir em na opção de autenticação ou se estiver usando outra forma de comunicação
informar no Header a chave  `Authorization` e o valor `Bearer conteudo do token retornado`

<div align="center">
  <img src="https://user-images.githubusercontent.com/30123041/198751245-ea9f9227-a79d-44ea-ab5a-4ccea8098aa0.png"/>
</div>  


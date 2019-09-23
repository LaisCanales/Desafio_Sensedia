# Desafio Sensedia

Desafio Sensedia api Facebook


## Requisitos

 - O usuário deve realizar a autenticação na api.
 - O usuário deve postar uma mensagem na página desse mesmo usuário.
 - Realizar uma alteração na mensagem postada acima.

### Configuracoes
Para rodar os testes foram ultilizados as seguintes dependencias:

    - Rest Assured
    - JUnit
    - cucumber

https://mvnrepository.com/


### Casos de Teste (BDD):

### Pré - Condições:
    Criar um APP em https://developers.facebook.com
    Atribuir um usuário de teste com as permissões para realização das requisições
    Gerar um token de acesso do usuário, com vencimento estendido em https://developers.facebook.com/tools/debug/accesstoken/
    Criar uma página para o usuário de teste, concedendo as permissões necessárias para realização das requisições
    Gerar um token de acesso da página, com vencimento estendido em https://developers.facebook.com/tools/debug/accesstoken/


### Casos de teste

### Caso de teste 01: Autenticação com um token inválido
    Dado O usuario possui um token invalido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me"
    Então deverá retornar o status 400
    E a autenticacao nao sera autorizada
	
### Caso de teste 02: Autenticação com um token válido
    Dado O usuario possui um token valido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me"
    Então A resposta do sistema deve ser 200
    E O sistema é autenticado com sucesso

### Caso de teste 03: Post no feed
    Dado O usuario possui uma autenticacao com um token valido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me/feed"
    E Postar a mensagem:"Desafio Sensedia" em sua pagina
    Então deverá retornar o status 200
    E a publicação deverá ser realizada


### Caso de teste 04: Alteração do post realizado
    Dado O usuario precisa realizar uma alteracao em seu post
    Quando Enviar uma requisicao para "https://graph.facebook.com/"
    E Alterar a mensagem para:"Desafio Sensedia Api Facebook"
    Então deverá retornar o status 200
    E a publicação deverá ser alterada

### Caso de teste 05: Exclusão do post realizado
    Dado O usuario precisa deletar a mensagem postada em sua pagina
    Quando Enviar uma requisicao utilizando delete para "https://graph.facebook.com/me/feed"
    Então deverá retornar o status 200
    E a publicação deverá ser deletada


### Rodando os testes

- Clonar o projeto no github [https://github.com/LaisCanales/Desafio_Sensedia]
- Importar o projeto em uma IDE
- Rodar a classe StepsDefinition/Steps
- Resultado será exibido no console



## Autor

* **Lais Alana Rodrigues Canales** - *Desafio Sensedia* - (https://github.com/LaisCanales)


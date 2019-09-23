Feature: Teste API do facebook
  Autenticacao
  Post e alteracao

  Scenario: O usuario realiza uma autenticacao com um token invalido
    Given O usuario possui um token invalido
    When Enviar uma requisicao para "https://graph.facebook.com/"
    Then A resposta do sistema deve ser 400

  Scenario: O usuario realiza uma autenticacao com um token valido
    Given O usuario possui um token valido
    When Enviar uma requisicao para "https://graph.facebook.com/"
    Then A resposta do sistema deve ser 200
    And o sistema e autenticado com sucesso

  Scenario: O usuario realiza um post de uma mensagem em sua pagina
    Given O usuario possui um token valido
    When Enviar uma requisicao para "https://graph.facebook.com/me/feed"
    And Postar a mensagem "{\"message\":\"Desafio Sensedia\"}" em sua pagina
    Then A resposta do sistema deve ser 200
    And O ID do post deve ser salvo

  Scenario: O usuario deve fazer uma alteracao no post realizado em sua pagina
    Given O usuario precisa realizar uma alteracao em seu post
    When Enviar uma requisicao para "https://graph.facebook.com/me/feed"
    And Alterar a mensagem para "{\"message\":\"Desafio Sensedia Api Facebook\"}"
    Then A resposta do sistema deve ser 200
    And O post deve ser alterado

  Scenario: O usuario deve deletar a mensagem postada em sua pagina
    Given O usuario precisa deletar a mensagem postada em sua pagina
    When Enviar uma requisicao utilizando delete para "https://graph.facebook.com/me/feed"
    Then A resposta do sistema deve ser 200
    And O Post deve ser deletado

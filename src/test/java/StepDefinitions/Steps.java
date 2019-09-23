package StepDefinitions;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;
import static javafx.scene.input.DataFormat.URL;

public class Steps {

    @RunWith(Cucumber.class)
    public class MyStepDefinitions {

        private String authToken;
        private String useURL;
        private String postID;
        private String Menssagem;
        Response reqResp;
        private String urlTokenAcces = "https://graph.facebook.com/me/?access_token=";
        private String graphUrl = "https://graph.facebook.com/";
        private Object idPost;

        //passo 1
        @Given("^O usuario possui um token invalido$")
        public void o_usuario_possui_um_token_invalido ( ) throws Throwable {
            this.authToken = "EAAeI2RZB1MgMBAPunKADKSZBlCO7Lxkt4kZArlXysJ5xZApVCKQXPumuK1NLwQ3ZCOcccRcWnEX4DEgG21SSy8i6D66oJuoyHZBMAZBkkyQszVuElKGVRE2S4YQUCz6zykorOvo8VUNnkSzUcwf7hoZCjyEbwiaxDxZCwpn5wZCtXZBGgZAOcwyuNt8K0Pg1WlClUZAY2Qlg0RNaYPwZDZ#";
            throw new PendingException();
        }

        @When("^Enviar uma requisicao para \"(.*)\"$")
        public void enviar_uma_requisicao_para (String strArg1) throws Throwable {
            this.useURL = URL + "/?access_token=" + authToken;
            reqResp = RestAssured.get(this.useURL);
        }

        @Then("^A resposta do sistema deve ser 400$")
        public void a_resposta_do_sistema_deve_ser_400 ( ) throws Throwable {
            if (reqResp.getStatusCode() == 400) {
                System.out.println(reqResp.getStatusCode() + " - sucesso");
            } else {
                System.out.println(reqResp.getStatusCode() + " - Erro");
            }
            Assert.assertEquals(400, reqResp.getStatusCode());
            throw new PendingException();

        }

        //passo 2
        @Given("^O usuario possui um token valido$")
        public void o_usuario_possui_um_token_valido ( ) throws Throwable {
            this.authToken = "EAAeI2RZB1MgMBAPunKADKSZBlCO7Lxkt4kZArlXysJ5xZApVCKQXPumuK1NLwQ3ZCOcccRcWnEX4DEgG21SSy8i6D66oJuoyHZBMAZBkkyQszVuElKGVRE2S4YQUCz6zykorOvo8VUNnkSzUcwf7hoZCjyEbwiaxDxZCwpn5wZCtXZBGgZAOcwyuNt8K0Pg1WlClUZAY2Qlg0RNaYPwZDZD";

            throw new PendingException();
        }


        @Then("^A resposta do sistema deve ser 200$")
        public void a_resposta_do_sistema_deve_ser_20 ( ) throws Throwable {
            if (reqResp.getStatusCode() == 200) {
                System.out.println(reqResp.getStatusCode() + " - Sucesso");
            } else {
                System.out.println(reqResp.getStatusCode() + " - Erro");
            }

        }

        @And("^Ele e autenticado com sucesso$")
        public void ele_e_autenticado_com_sucesso ( ) throws Throwable {
            Assert.assertEquals(200, reqResp.getStatusCode());
            throw new PendingException();
        }

        //Passo 3


        @And("^Postar a mensagem \"(.*)\" em sua pagina$")
        public void postar_a_mensagem_messagedesafio_sensedia_api_em_sua_pagina (String menssagem) throws Throwable {

            Menssagem = menssagem;
            Response postReq = given()
                    .contentType(ContentType.JSON)
                    .body("{\"message\":\"Desafio Sensedia\"}")
                    .when()
                    .post(urlTokenAcces + authToken);

            if (postReq.getStatusCode() == 200) {

                System.out.println(postReq.getStatusCode() + "Sucesso");
                this.postID = postReq.
                        then().
                        contentType(ContentType.JSON).extract().path("id");
                System.out.println(this.postID + "- id salvo");
            } else {
                System.out.println(postReq.getStatusCode() + "Erro");
            }
        }

        @And("^O ID do post deve ser salvo$")
        public void o_id_do_post_deve_ser_salvo ( ) throws Throwable {

            this.idPost = reqResp.
                    then().
                    contentType(ContentType.JSON).extract().path("id");
            System.out.println(this.idPost + "- id salvo");
            throw new PendingException();
        }


        //passo 4
        @Given("^O usuario precisa realizar uma alteracao em seu post$")
        public void o_usuario_precisa_realizar_uma_alteracao_em_seu_post ( ) throws Throwable {
            this.authToken = "EAAeI2RZB1MgMBAPunKADKSZBlCO7Lxkt4kZArlXysJ5xZApVCKQXPumuK1NLwQ3ZCOcccRcWnEX4DEgG21SSy8i6D66oJuoyHZBMAZBkkyQszVuElKGVRE2S4YQUCz6zykorOvo8VUNnkSzUcwf7hoZCjyEbwiaxDxZCwpn5wZCtXZBGgZAOcwyuNt8K0Pg1WlClUZAY2Qlg0RNaYPwZDZD";

            throw new PendingException();
        }

        @And("^Alterar a mensagem para \"(.*)\"$")
        public void alterar_a_mensagem_para_something (String strArg1, String strArg2, String strArg3) throws Throwable {

            Response changeReq = given()
                    .contentType(ContentType.JSON)
                    .body("{\"message\":\"Desafio Sensedia\"}")
                    .when()
                    .put(graphUrl + postID + "/?access_token=" + authToken);

            if (changeReq.getStatusCode() == 200) {
                System.out.println(changeReq.getStatusCode() + "Sucesso);
            } else {
                System.out.println(changeReq.getStatusCode() + "Erro");
            }


            @And("^O post deve ser alterado$")
            public void o_post_deve_ser_alterado () throws Throwable {
                reqResp = given()
                        .contentType(ContentType.JSON)
                        .body("{\"message\":\"Desafio Sensedia Api Facebook\"}")
                        .when()
                        .put(graphUrl + idPost + authToken);

                if (reqResp.getStatusCode() == 200) {
                    System.out.println(reqResp.getStatusCode() + "Sucesso ");
                } else {
                    System.out.println(reqResp.getStatusCode() + "Erro");
                }
                Assert.assertEquals(200, reqResp.getStatusCode());

                throw new PendingException();
            }

        }
    }
}
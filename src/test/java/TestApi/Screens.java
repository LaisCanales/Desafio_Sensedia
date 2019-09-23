package TestApi;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class Screens {
    private String authToken = "EAAeI2RZB1MgMBAPunKADKSZBlCO7Lxkt4kZArlXysJ5xZApVCKQXPumuK1NLwQ3ZCOcccRcWnEX4DEgG21SSy8i6D66oJuoyHZBMAZBkkyQszVuElKGVRE2S4YQUCz6zykorOvo8VUNnkSzUcwf7hoZCjyEbwiaxDxZCwpn5wZCtXZBGgZAOcwyuNt8K0Pg1WlClUZAY2Qlg0RNaYPwZDZD";
    private String authTokenInvalid = "EAAeI2RZB1MgMBAPunKADKSZBlCO7Lxkt4kZArlXysJ5xZApVCKQXPumuK1NLwQ3ZCOcccRcWnEX4DEgG21SSy8i6D66oJuoyHZBMAZBkkyQszVuElKGVRE2S4YQUCz6zykorOvo8VUNnkSzUcwf7hoZCjyEbwiaxDxZCwpn5wZCtXZBGgZAOcwyuNt8K0Pg1WlClUZAY2Qlg0RNaYPwZDZ#";
    private String postID;
    private String urlTokenAcess = "https://graph.facebook.com/me/?access_token=";
    private String graphUrl = "https://graph.facebook.com/me";


    @Test
    public void verifyAuthenticationInvalid ( ) throws Exception {


        Response resp = RestAssured.get(urlTokenAcess + authTokenInvalid);

        if (resp.getStatusCode() == 400) {

            System.out.println(resp.getStatusCode() + " - Sucesso");
        } else {
            System.out.println(resp.getStatusCode() + "Erro");
        }
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test
    public void verifyAuthentication ( ) throws Exception {

        Response resp = RestAssured.get(urlTokenAcess + authToken);

        if (resp.getStatusCode() == 200) {

            System.out.println(resp.getStatusCode() + " Sucesso");
        } else {
            System.out.println(resp.getStatusCode() + "Erro");
        }
        Assert.assertEquals(200, resp.getStatusCode());
    }


    @Test
    public void postTimeLine ( ) {


        Response postReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"Desafio Sensedia\"}")
                .when()
                .post(graphUrl + authToken);

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

    @Test
    public void changePostTimeLine ( ) {

        Response changeReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"Desafio Sensedia Api Facebook\"}")
                .when()
                .put(graphUrl + postID + authToken);

        if (changeReq.getStatusCode() == 200) {
            System.out.println(changeReq.getStatusCode() + "Sucesso");
        } else {
            System.out.println(changeReq.getStatusCode() + "Erro");
        }
    }

    @Test
    public void deletePostTimeLine ( ) {

        ResponsepostTimeLine responsePageTimeLine = postTimeLine();

        String updatedUrl = responsePageTimeLine.postID + authToken;
        Response res =
                given()
                        .when()
                        .contentType(ContentType.JSON)
                        .delete(graphUrl + updatedUrl);
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}

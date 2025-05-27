package app.controllers;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ChampionControllerTest {
    @BeforeAll
    static void setup() {
        io.restassured.RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    void testCreateChampionUnauthorized() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Zed\"}")
                .when().post("/champions")
                .then().statusCode(401);  // Unauthorized request
    }
}

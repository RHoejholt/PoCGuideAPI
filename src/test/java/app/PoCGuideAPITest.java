package app;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class PoCGuideAPITest {
    @Test
    public void testGetChampionAndItemsById() {
        RestAssured.baseURI = "http://localhost:7000";

        given()
                .when()
                .get("/champions/1")
                .then()
                .statusCode(200)
                .body("champion.name", is("Fiddlesticks"))
                .body("champion.description", containsString("scary"));
    }
}
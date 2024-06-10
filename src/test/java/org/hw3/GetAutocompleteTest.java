package org.hw3;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hw3.autocomplete.Autocomplete;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAutocompleteTest extends AccuweatherAbstractTest {

    @Test
    void getLocation_Ufa() {

        List<Autocomplete> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Ufa")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Autocomplete.class);

        Assertions.assertEquals("Ufa", response.get(0).getLocalizedName());
//        System.out.println(response.get(0).getKey());
    }
}
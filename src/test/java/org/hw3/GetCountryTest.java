package org.hw3;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hw3.autocomplete.Autocomplete;
import org.hw3.autocomplete.Country;


import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCountryTest extends AccuweatherAbstractTest {
    @Test
    void GetLocalizedNameTest() {
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
    }
}
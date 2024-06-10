package org.hw3;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hw3.getonehour.Getonehour ;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetOneHourTest extends AccuweatherAbstractTest{
    @Test
    void GetOneHourTest() {
        List<Getonehour> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("metric", true)
                .when()
                .get(getBaseUrl()+"/forecasts/v1/hourly/1hour/292177")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body()
                .jsonPath().getList(".", Getonehour.class);

        Assertions.assertFalse(response.get(0).getHasPrecipitation());

    }
}
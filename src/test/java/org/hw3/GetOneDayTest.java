package org.hw3;

import org.hamcrest.Matchers;
import org.hw3.getoneday.Getoneday;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hw3.getoneday.DailyForecast ;

import static io.restassured.RestAssured.given;

public class GetOneDayTest extends AccuweatherAbstractTest{
    @Test
    void GetDailyForecastTest() {

        Getoneday response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("metric", true)
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/292177")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                        .body().as(Getoneday.class);

        Assertions.assertEquals("C", response.getDailyForecasts().get(0).getTemperature().getMinimum().getUnit());
        Assertions.assertEquals(12.3, response.getDailyForecasts().get(0).getTemperature().getMinimum().getValue());
    }
}
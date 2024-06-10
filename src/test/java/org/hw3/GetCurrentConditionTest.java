package org.hw3;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hw3.autocomplete.Autocomplete;
import org.hw3.currentcondition.Currentcondition;
import org.hw3.currentcondition.Imperial;
import org.hw3.currentcondition.Metric;
import org.hw3.currentcondition.Temperature;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCurrentConditionTest extends AccuweatherAbstractTest {
    @Test
    void GetTemperatureTest() {
        List<Currentcondition> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Ufa")
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/292177")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body()
                .jsonPath().getList(".", Currentcondition.class);

        Assertions.assertEquals("C", response.get(0).getTemperature().getMetric().getUnit());
        Assertions.assertEquals(18.9, response.get(0).getTemperature().getMetric().getValue());
    }
}
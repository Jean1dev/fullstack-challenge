package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.CinemaDTO;
import com.willian.AlpacaFilmes.integrationsTest.configs.TestConfigs;
import com.willian.AlpacaFilmes.integrationsTest.testContainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CinemaControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private CinemaDTO cinemaDTO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    @Order(0)
    @DisplayName("Teste Api authorization")
    public void authorization() {

        specification = new RequestSpecBuilder()
                .setBasePath("v1/cinema")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste resgatarCinema deve retornar uma lista de objetos do tipo CinemaDTO")
    public void testFindAll() throws IOException {

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<CinemaDTO>>() {});

        CinemaDTO cinemaDTO1 = content.getFirst();

        assertNotNull(cinemaDTO1.getId(), () -> "Id do cinema não deveria ser nulo");
        assertNotNull(cinemaDTO1.getNome(), () -> "Nome do cimena não deveria ser nulo");
        Assertions.assertNotNull(cinemaDTO1, () -> "Objeto retornado não deveria ser nulo");

        Assertions.assertEquals(1L, cinemaDTO1.getId(), () -> "Id do cinema deveria ser 1");
        Assertions.assertEquals("Alpaca Cinemas", cinemaDTO1.getNome(), () -> "Nome do cinema deveria ser Alpaca Cinemas");
    }
}

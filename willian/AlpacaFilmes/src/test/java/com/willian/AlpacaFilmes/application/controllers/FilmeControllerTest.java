package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.FilmeDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilmeControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

    private static FilmeDTO filmeBase;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    @Order(0)
    @DisplayName("Test Api authorization")
    public void authorization() {

        specification = new RequestSpecBuilder()
                .setBasePath("v1/filmes")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Encontrar todos deve retornar uma lista de FilmesDTO")
    public void testFindAll() throws IOException {

        List<FilmeDTO> content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        FilmeDTO filmeDTO = content.getFirst();
        filmeBase = content.getFirst();

        assertNotNull(filmeDTO, () -> "Filme retornado não deveria ser nulo");

        assertNotNull(filmeDTO.getId(), () -> "Id do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getTitle(), () -> "Título do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getOverview(), () -> "Overview do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getPosterPath(), () -> "O poster path do filme retornado não deveria ser nulo");

        FilmeDTO filmeDTO1 = content.get(3);
        assertNotNull(filmeDTO1.getId(), () -> "Id do filme1 retornado não deveria ser nulo");
        assertNotNull(filmeDTO1.getTitle(), () -> "Título do filme1 retornado não deveria ser nulo");
        assertNotNull(filmeDTO1.getOverview(), () -> "Overview do filme1 retornado não deveria ser nulo");
        assertNotNull(filmeDTO1.getPosterPath(), () -> "O poster path do filme1 retornado não deveria ser nulo");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo FilmeDTO")
    public void testFindById() throws IOException {
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", filmeBase.getId()).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        FilmeDTO filmeDTO = objectMapper.readValue(content, FilmeDTO.class);

        assertNotNull(filmeDTO, () -> "Filme retornado não deveria ser nulo");

        assertNotNull(filmeDTO.getId(), () -> "Id do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getTitle(), () -> "Título do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getOverview(), () -> "Overview do filme retornado não deveria ser nulo");
        assertNotNull(filmeDTO.getPosterPath(), () -> "O poster path do filme retornado não deveria ser nulo");

        assertEquals(filmeBase.getId(), filmeDTO.getId(), () -> "O Id do filme retornado deveria ser igual ao id do filme base");
        assertEquals(filmeBase.getTitle(), filmeDTO.getTitle(), () -> "O título do filme base deveria ser o mesmo do filme retornado");
        assertEquals(filmeBase.getOverview(), filmeDTO.getOverview(), () -> "O overview do filme base deveria ser o memso do filme retornado");
        assertEquals(filmeBase.getPosterPath(), filmeDTO.getPosterPath(), () -> "O poster path deveria do filme base deveria ser o mesmo do filme retornado");
    }
}

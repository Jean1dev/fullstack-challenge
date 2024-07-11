package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.ProgramacaoDTO;
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
public class ProgramacaoControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

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
                .setBasePath("/v1/programacao")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Encontrar todos deve retornar uma lista de ProgramacaoDTO")
    public void testFindAll() throws IOException {

        List<ProgramacaoDTO> content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<ProgramacaoDTO>>() {
                });

        ProgramacaoDTO programacaoDTO1 = content.getFirst();

        assertNotNull(programacaoDTO1, () -> "Programação retornada não deveria ser nulo");

        assertNotNull(programacaoDTO1.getId(), () -> "Id da programação1 retornada não deveria ser nulo");
        assertNotNull(programacaoDTO1.getFilme(), () -> "Filme da programação não deveria ser nulo");
        assertNotNull(programacaoDTO1.getSala(), () -> "Salas da programação não deveria ser nulo");
        assertNotNull(programacaoDTO1.getHorarios(), () -> "Horários não deveria ser nulo");

        ProgramacaoDTO programacaoDTO2 = content.get(3);
        assertNotNull(programacaoDTO2.getId(), () -> "Id da programação2 retornada não deveria ser nulo");
        assertNotNull(programacaoDTO2.getFilme(), () -> "Filme da programação não deveria ser nulo");
        assertNotNull(programacaoDTO2.getSala(), () -> "Salas da programação não deveria ser nulo");
        assertNotNull(programacaoDTO2.getHorarios(), () -> "Horários não deveria ser nulo");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo FilmeDTO")
    public void testFindById() throws IOException {
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", 2).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        ProgramacaoDTO programacaoDTO1 = objectMapper.readValue(content, ProgramacaoDTO.class);

        assertNotNull(programacaoDTO1, () -> "Programação retornada não deveria ser nulo");

        assertNotNull(programacaoDTO1.getId(), () -> "Id da programação1 retornada não deveria ser nulo");
        assertNotNull(programacaoDTO1.getFilme(), () -> "Filme da programação não deveria ser nulo");
        assertNotNull(programacaoDTO1.getSala(), () -> "Salas da programação não deveria ser nulo");
        assertNotNull(programacaoDTO1.getHorarios(), () -> "Horários não deveria ser nulo");
    }

}

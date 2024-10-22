package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.TipoIngressoDTO;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoIngressoControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

    private static TipoIngressoDTO tipoIngressoBase;

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
                .setBasePath("/v1/tipo-ingresso")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Encontrar todos os tipos de ingressos deve retornar uma lista de Tipo Ingresso DTO")
    public void testFindAll() throws IOException {

        List<TipoIngressoDTO> content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        TipoIngressoDTO tipoIngressoDTO = content.getFirst();
        tipoIngressoBase = content.getFirst();

        assertNotNull(tipoIngressoDTO, () -> "Tipo Ingresso retornado não deveria ser nulo");

        assertNotNull(tipoIngressoDTO.getId(), () -> "Id do Tipo Ingresso retornado não deveria ser nulo");
        assertNotNull(tipoIngressoDTO.getTipoIngresso(), () -> "O nome do Tipo Ingresso retornado não deveria ser nulo");
        assertTrue(tipoIngressoDTO.getPreco() >= 0.0, () -> "O preço do Tipo Ingresso retornado não deveria ser 0.0");

        TipoIngressoDTO tipoIngressoDTO1 = content.getLast();
        assertNotNull(tipoIngressoDTO1.getId(), () -> "Id do Tipo Ingresso retornado não deveria ser nulo");
        assertNotNull(tipoIngressoDTO1.getTipoIngresso(), () -> "O nome do Tipo Ingresso retornado não deveria ser nulo");
        assertTrue(tipoIngressoDTO1.getPreco() >= 0.0, () -> "O preço do Tipo Ingresso retornado não deveria ser 0.0");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo Tipo Ingresso DTO")
    public void testFindById() throws IOException {
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", tipoIngressoBase.getId()).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        TipoIngressoDTO tipoIngressoDTO = objectMapper.readValue(content, TipoIngressoDTO.class);

        assertNotNull(tipoIngressoDTO, () -> "Tipo Ingresso retornado não deveria ser nulo");

        assertNotNull(tipoIngressoDTO.getId(), () -> "Id do Tipo Ingresso retornado não deveria ser nulo");
        assertNotNull(tipoIngressoDTO.getTipoIngresso(), () -> "O nome do Tipo Ingresso retornado não deveria ser nulo");
        assertTrue(tipoIngressoDTO.getPreco() >= 0.0, () -> "O preço do Tipo Ingresso retornado não deveria ser 0.0");

        assertEquals(tipoIngressoDTO.getId(), tipoIngressoBase.getId(), () -> "O Id do Tipo Ingresso retornado deveria ser igual ao id do Tipo Ingresso base");
        assertEquals(tipoIngressoDTO.getTipoIngresso(), tipoIngressoBase.getTipoIngresso(), () -> "O nome do Tipo Ingresso retornado deveria ser o mesmo do Tipo Ingresso base");
        assertEquals(tipoIngressoDTO.getPreco(), tipoIngressoBase.getPreco(), () -> "O preço do Tipo Ingresso retornado deveria ser o memso do Tipo Ingresso base");
    }
}

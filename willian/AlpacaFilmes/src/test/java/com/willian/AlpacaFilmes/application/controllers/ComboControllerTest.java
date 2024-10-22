package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.ComboDTO;
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
public class ComboControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static ComboDTO comboBase;

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
                .setBasePath("/v1/combos")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Encontrar todos os combos deve retornar uma lista de CombosDTO")
    public void testFindAll() throws IOException {

        List<ComboDTO> content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        ComboDTO comboDTO = content.getFirst();
        comboBase = content.getFirst();

        assertNotNull(comboDTO, () -> "Combos retornados não deveriam ser nulos");

        assertNotNull(comboDTO.getId(), () -> "Id do Combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getNome(), () -> "O nome do combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getPreco(), () -> "O preço do Combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getItens(), () -> "Os itens do combo retornado não deveria ser nulo");

        ComboDTO comboDTO1 = content.getLast();
        assertNotNull(comboDTO1.getId(), () -> "Id do Combo1 retornado não deveria ser nulo");
        assertNotNull(comboDTO1.getNome(), () -> "O nome do combo retornado não deveria ser nulo");
        assertNotNull(comboDTO1.getPreco(), () -> "O preço do Combo retornado não deveria ser nulo");
        assertNotNull(comboDTO1.getItens(), () -> "Os itens do combo retornado não deveria ser nulo");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo ComboDTO")
    public void testFindById() throws IOException {
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", comboBase.getId()).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        ComboDTO comboDTO = objectMapper.readValue(content, ComboDTO.class);

        assertNotNull(comboDTO, () -> "Filme retornado não deveria ser nulo");

        assertNotNull(comboDTO.getId(), () -> "Id do Combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getNome(), () -> "O nome do combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getPreco(), () -> "O preço do Combo retornado não deveria ser nulo");
        assertNotNull(comboDTO.getItens(), () -> "Os itens do combo retornado não deveria ser nulo");

        assertEquals(comboDTO.getId(), comboBase.getId(), () -> "O Id do combo retornado deveria ser igual ao id do combo base");
        assertEquals(comboDTO.getNome(), comboBase.getNome(), () -> "O nome do combo retornando deveria ser o mesmo do combo base");
        assertEquals(comboDTO.getPreco(), comboBase.getPreco(), () -> "O preço do combo retornado deveria ser o memso do combo base");
        assertEquals(comboDTO.getItens().getFirst().getId(), comboBase.getItens().getFirst().getId(), () -> "O Id do primeiro item do combo retornado deveria ser o mesmo do combo base");
    }
}

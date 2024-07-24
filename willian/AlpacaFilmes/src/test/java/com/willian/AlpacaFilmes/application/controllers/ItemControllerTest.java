package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.ItemDTO;
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
public class ItemControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

    private static ItemDTO itemBase;

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
                .setBasePath("/v1/items")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Encontrar todos os itens deve retornar uma lista de ItemDTO")
    public void testFindAll() throws IOException {

        List<ItemDTO> content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        ItemDTO itemDTO = content.getFirst();
        itemBase = content.getFirst();

        assertNotNull(itemDTO, () -> "Item retornado não deveria ser nulo");

        assertNotNull(itemDTO.getId(), () -> "Id do filme retornado não deveria ser nulo");
        assertNotNull(itemDTO.getNome(), () -> "O nome do item retornado não deveria ser nulo");
        assertNotNull(itemDTO.getPreco(), () -> "O preço do item retornado não deveria ser nulo");

        ItemDTO itemDTO1 = content.getLast();
        assertNotNull(itemDTO1.getId(), () -> "Id do item retornado não deveria ser nulo");
        assertNotNull(itemDTO1.getNome(), () -> "O nome do item retornado não deveria ser nulo");
        assertNotNull(itemDTO1.getPreco(), () -> "O preço do item retornado não deveria ser nulo");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo ItemDTO")
    public void testFindById() throws IOException {
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", itemBase.getId()).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        ItemDTO itemDTO = objectMapper.readValue(content, ItemDTO.class);

        assertNotNull(itemDTO, () -> "Item retornado não deveria ser nulo");

        assertNotNull(itemDTO.getId(), () -> "Id do Item retornado não deveria ser nulo");
        assertNotNull(itemDTO.getNome(), () -> "O nome do item retornado não deveria ser nulo");
        assertNotNull(itemDTO.getPreco(), () -> "O preço do item retornado não deveria ser nulo");

        assertEquals(itemDTO.getId(), itemBase.getId(), () -> "O Id do item retornado deveria ser igual ao id do item base");
        assertEquals(itemDTO.getNome(), itemBase.getNome(), () -> "O nome do item retornado deveria ser o mesmo do item base");
        assertEquals(itemDTO.getPreco(), itemBase.getPreco(), () -> "O preço do item retornado deveria ser o memso do item base");
    }
}

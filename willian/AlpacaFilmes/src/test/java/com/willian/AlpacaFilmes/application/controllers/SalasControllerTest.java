package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.services.SalasServices;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
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
import org.mockito.Mock;
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
public class SalasControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static SalasDTO salasDTO;

    @Mock
    private SalasServices salasServices;

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
                .setBasePath("v1/salas")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste buscar todos deve retornar uma lista de objetos do tipo SalasDTO")
    public void testFindAll() throws IOException {

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<SalasDTO>>() {
                });

        SalasDTO salasDTO = content.getFirst();
        SalasDTO salasDTO1 = content.get(3);

        assertNotNull(salasDTO.getId(), () -> "Id da sala não deveria ser nulo");
        assertNotNull(salasDTO.getNumero(), () -> "Numero da sala não deveria ser nulo");
        Assertions.assertNotNull(salasDTO, () -> "Objeto retornado não deveria ser nulo");

        Assertions.assertEquals(1L, salasDTO.getId(), () -> "Id da sala deveria ser 1");
        Assertions.assertEquals(1, salasDTO.getNumero(), () -> "Numero da sala deveria ser 1");

        assertNotNull(salasDTO1.getId(), () -> "Id da sala não deveria ser nulo");
        assertNotNull(salasDTO1.getNumero(), () -> "Numero da sala não deveria ser nulo");
        Assertions.assertNotNull(salasDTO1, () -> "Objeto retornado não deveria ser nulo");

        Assertions.assertEquals(4L, salasDTO1.getId(), () -> "Id da sala deveria ser 4");
        Assertions.assertEquals(4, salasDTO1.getNumero(), () -> "Numero da sala deveria ser 4");
    }

    @Test
    @Order(2)
    @DisplayName("Teste buscar Por Id deve retornar um Objeto do tipo FilmeDTO")
    public void testFindById() throws IOException {
        salasDTO = new SalasDTO(1L, 1, List.of());
        String content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", salasDTO.getId()).when().get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        SalasDTO salasDTO1 = objectMapper.readValue(content, SalasDTO.class);

        assertNotNull(salasDTO1, () -> "A sala retornada não deveria ser nulo");

        assertNotNull(salasDTO.getId(), () -> "Id da sala não deveria ser nulo");
        assertNotNull(salasDTO.getNumero(), () -> "Numero da sala não deveria ser nulo");
        Assertions.assertNotNull(salasDTO, () -> "Objeto retornado não deveria ser nulo");

        Assertions.assertEquals(salasDTO.getId(), salasDTO.getId(), () -> "Id da sala deveria ser 1");
        Assertions.assertEquals(salasDTO.getNumero(), salasDTO.getNumero(), () -> "Numero da sala deveria ser 1");
    }

    @Test
    @Order(3)
    @DisplayName("Teste buscar Cadeiras deve retornar uma lsita de Objetos do tipo CaderiasDTO")
    public void testbuscarCadeiras() throws IOException {
        salasDTO = new SalasDTO(1L, 1, List.of());

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParams("id", salasDTO.getId()).when().get("/{id}/cadeiras")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<CadeirasDTO>>() {
                });

        CadeirasDTO cadeirasDTO = content.getFirst();

        CadeirasDTO cadeirasDTO1 = content.get(3);

        assertNotNull(content, () -> "A Lista de caderias retornada não deveria ser nulo");

        assertNotNull(cadeirasDTO.getId(), () -> "Id da cadeiras não deveria ser nulo");
        assertNotNull(cadeirasDTO.getNumero(), () -> "Numero da cadeiras não deveria ser nulo");
        Assertions.assertNotNull(cadeirasDTO, () -> "Objeto retornado não deveria ser nulo");

        assertNotNull(cadeirasDTO1.getId(), () -> "Id da cadeiras não deveria ser nulo");
        assertNotNull(cadeirasDTO1.getNumero(), () -> "Numero da cadeiras não deveria ser nulo");
        Assertions.assertNotNull(cadeirasDTO1, () -> "Objeto retornado não deveria ser nulo");
    }
}

package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.domain.dto.CadeiraStatusDTO;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.integrationsTest.configs.TestConfigs;
import com.willian.AlpacaFilmes.integrationsTest.testContainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadeiraControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static CadeirasDTO cadeirasDTO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

       cadeirasDTO = new CadeirasDTO();
    }

    @Test
    @Order(0)
    @DisplayName("Test Api authorization")
    public void authorization() {

        specification = new RequestSpecBuilder()
                .setBasePath("v1/caderias")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Teste update status ao atualizar o status de uma caderia")
    public void testUpdateStatus() throws IOException {
        mockCadeiras();
        CadeiraStatusDTO statusDTO = new CadeiraStatusDTO(CadeiraStatus.OCUPADA);
        //Given / Arrange
        given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(statusDTO)
                .pathParam("id", cadeirasDTO.getId())
                // When / Act
                .when().put("/{id}")
                //Then /Assert
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void mockCadeiras() {
        cadeirasDTO.setId(1L);
        cadeirasDTO.setNumero(1);
        cadeirasDTO.setStatus(CadeiraStatus.LIVRE);
    }
}

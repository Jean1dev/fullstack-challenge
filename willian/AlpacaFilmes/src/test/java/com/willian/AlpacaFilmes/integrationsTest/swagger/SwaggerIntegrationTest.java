package com.willian.AlpacaFilmes.integrationsTest.swagger;

import com.willian.AlpacaFilmes.integrationsTest.configs.TestConfigs;
import com.willian.AlpacaFilmes.integrationsTest.testContainers.AbstractIntegrationTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @DisplayName("teste de integração da OpenAPI(Swagger)")
    @Test
    public void testSwaggerIntegration() {
    	//Given / Arrange / When / Act
        String content = given().basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();
    	//Then /Assert
        assertTrue(content.contains("Swagger UI"), () -> "Deveria conter a String Swagger UI");
    }
}

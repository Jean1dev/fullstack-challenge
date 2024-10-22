package com.willian.AlpacaFilmes.application.controllers.interfaces;

import com.willian.AlpacaFilmes.domain.dto.TipoIngressoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Tipo Ingresso", description = "Endpoints para buscar informações sobre os tipos de ingressos disponíveis")
public interface ITipoIngressoController {

    @Operation(
            summary = "Endpoints para encontrar informações de todos os tipos de ingressos",
            description = "Com esse endpoint você pode resgatar todas as informações relevantes sobre tipos de ingressos " +
                    "que podem ser vendidos",
            tags = {"Tipo Ingresso"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = TipoIngressoDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<List<TipoIngressoDTO>> buscarTodos();

    @Operation(
            summary = "Endpoints para encontrar informações de tipo de ingresso",
            description = "Com esse endpoint você pode resgatar todas as informações relevantes sobre um unico tipo de ingresso" +
                    "que pode ser vendidos",
            tags = {"Tipo Ingresso"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = TipoIngressoDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<TipoIngressoDTO> buscarPorId(Long id);
}

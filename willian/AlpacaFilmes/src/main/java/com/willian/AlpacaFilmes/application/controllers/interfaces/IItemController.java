package com.willian.AlpacaFilmes.application.controllers.interfaces;

import com.willian.AlpacaFilmes.domain.dto.ItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Items", description = "Endpoints para buscar informações sobre os items dos combos")
public interface IItemController {

    @Operation(
            summary = "Endpoints para encontrar informações de todos os items dos combos",
            description = "Com esse endpoint você pode resgatar todas as informações relevantes sobre os items " +
                    "que podem ser vendidos nos combos",
            tags = {"Items"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = ItemDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<List<ItemDTO>> buscarTodos();

    @Operation(
            summary = "Endpoints para encontrar informações de um item",
            description = "Com esse endpoint você pode resgatar todas as informações relevantes sobre um unico item " +
                    "que pode ser vendidos nos combos",
            tags = {"Items"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = ItemDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<ItemDTO> buscarPorId(Long id);
}

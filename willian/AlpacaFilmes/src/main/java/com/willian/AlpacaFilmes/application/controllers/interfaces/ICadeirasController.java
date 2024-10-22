package com.willian.AlpacaFilmes.application.controllers.interfaces;

import com.willian.AlpacaFilmes.domain.dto.CadeiraStatusDTO;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.dto.FilmeDTO;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name="Cadeiras", description = "Endpoints para buscar informações sobre as cadeiras disponiveis")
public interface ICadeirasController {
    @Operation(
            summary = "Endpoints para atualizar o status de uma cadeiras",
            description = "Com esse endpoint você pode atualizar o status de uma cadeira para LIVRE ou OCUPADO",
            tags = {"Cadeiras"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    ResponseEntity<?> updateStatus(Long id, CadeiraStatusDTO status);
}

package com.willian.AlpacaFilmes.application.controllers.interfaces;

import com.willian.AlpacaFilmes.domain.dto.CriarIngressoDTO;
import com.willian.AlpacaFilmes.domain.dto.IngressoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Ingresso", description = "Endpoints para encontrar e gerenciar informações dos ingressos")
public interface IIngressoController {
    @Operation(
            summary = "Criar um ingresso",
            description = "Esse endPoint pode ser utilizado para criar novos ingressos",
            tags = {"Ingresso"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = IngressoDTO.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    ResponseEntity<IngressoDTO> criar(CriarIngressoDTO ingressoDTO);
}

package com.fiap.mssistemalanchonetepedido.entrypoint.controller;

import com.fiap.mssistemalanchonetepedido.core.exception.ErrorResponse;
import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.usecase.PagamentoUseCaseFacade;
import com.fiap.mssistemalanchonetepedido.dataprovider.mapper.PagamentoDtoMapper;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PagamentoRequestDto;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PagamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoUseCaseFacade pagamentoUseCaseFacade;
    @Autowired
    private PagamentoDtoMapper pagamentoDtoMapper;

    @Operation(
            description = "Webhook para realização do pagamento e recebimento da confirmaçao",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso!"),
                    @ApiResponse(responseCode = "404",
                            description = "Pedido não encontrado!",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<Pedido> realizarPagamento(@RequestBody final PagamentoRequestDto pagamentoRequestDto) throws Exception {
        return ResponseEntity.ok(pagamentoUseCaseFacade.realizarPagamento(pagamentoRequestDto.getCodigoPedido()));
    }
}

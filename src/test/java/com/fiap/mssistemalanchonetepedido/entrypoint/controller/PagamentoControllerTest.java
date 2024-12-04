package com.fiap.mssistemalanchonetepedido.entrypoint.controller;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.usecase.PagamentoUseCaseFacade;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PagamentoRequestDto;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PagamentoResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PagamentoControllerTest {

    /*
    @Mock
    private PagamentoUseCaseFacade pagamentoUseCaseFacade;

    @InjectMocks
    private PagamentoController pagamentoController;

    @Test
    void realizarPagamento_Success() throws Exception {
        // Arrange
        PagamentoRequestDto requestDto = new PagamentoRequestDto();
        requestDto.setCodigoPedido("12345");
        Pedido expectedPedido = new Pedido(); // Mock ou crie um objeto Pedido conforme necessário

        when(pagamentoUseCaseFacade.realizarPagamento("12345")).thenReturn(expectedPedido);

        // Act
        ResponseEntity<Pedido> response = pagamentoController.realizarPagamento(requestDto);

        // Assert
        assertNotNull(response);
        assertEquals(expectedPedido, response.getBody());
        verify(pagamentoUseCaseFacade, times(1)).realizarPagamento("12345");
    }

    @Test
    void realizarPagamento_NotFound() throws Exception {
        // Arrange
        PagamentoRequestDto requestDto = new PagamentoRequestDto();
        requestDto.setCodigoPedido("12345");

        // Simula uma exceção de "Not Found"
        when(pagamentoUseCaseFacade.realizarPagamento("12345"))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            pagamentoController.realizarPagamento(requestDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, "400");
        assertTrue(exception.getReason().contains("Pedido não encontrado"));
    }

     */
}

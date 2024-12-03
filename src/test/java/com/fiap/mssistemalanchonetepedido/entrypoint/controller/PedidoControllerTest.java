package com.fiap.mssistemalanchonetepedido.entrypoint.controller;

import com.fiap.mssistemalanchonetepedido.core.model.Pedido;
import com.fiap.mssistemalanchonetepedido.core.usecase.PedidoUseCaseFacade;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PedidoResponseDto;
import com.fiap.mssistemalanchonetepedido.entrypoint.dto.PedidoResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PedidoControllerTest {

    /*
    private MockMvc mockMvc;

    @Autowired
    private PedidoUseCaseFacade pedidoUseCaseFacade;

    @Autowired
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        pedidoController = new PedidoController(pedidoUseCaseFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    // Teste de consulta de pedido por código
    @Test
    void testConsultarPedido_Success() throws Exception {
        String codigoPedido = "12345";
        Pedido pedido = new Pedido();
        pedido.setCodigo(codigoPedido);
        when(pedidoUseCaseFacade.getPedidoPorCodigo(codigoPedido)).thenReturn(pedido);

        mockMvc.perform(get("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("12345"));
    }

    @Test
    void testConsultarPedido_NotFound() throws Exception {
        String codigoPedido = "12345";
        when(pedidoUseCaseFacade.getPedidoPorCodigo(codigoPedido)).thenReturn(null);

        mockMvc.perform(get("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testConsultarPedido_Exception() throws Exception {
        String codigoPedido = "12345";
        when(pedidoUseCaseFacade.getPedidoPorCodigo(codigoPedido)).thenThrow(new RuntimeException("Erro inesperado"));

        mockMvc.perform(get("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    // Teste de criação de pedido
    @Test
    void testCriarPedido_Success() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setCodigo("12345");
        when(pedidoUseCaseFacade.criarPedido(Mockito.any(PedidoResponseDto.class))).thenReturn(pedido);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\": \"12345\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo").value("12345"));
    }


    @Test
    void testCriarPedido_Exception() throws Exception {
        when(pedidoUseCaseFacade.criarPedido(Mockito.any(PedidoResponseDto.class)))
                .thenThrow(new RuntimeException("Erro na criação do pedido"));

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\": \"12345\"}"))
                .andExpect(status().isInternalServerError());
    }

    // Teste de atualização de pedido
    @Test
    void testAtualizarPedido_Success() throws Exception {
        String codigoPedido = "12345";
        Pedido pedido = new Pedido();
        pedido.setCodigo(codigoPedido);
        when(pedidoUseCaseFacade.atualizarPedido(codigoPedido, Mockito.any(PedidoResponseDto.class))).thenReturn(pedido);

        mockMvc.perform(put("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\": \"12345\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("12345"));
    }

    @Test
    void testAtualizarPedido_NotFound() throws Exception {
        String codigoPedido = "12345";
        when(pedidoUseCaseFacade.atualizarPedido(codigoPedido, Mockito.any("asd")))
                .thenThrow(new RuntimeException("Pedido não encontrado"));

        mockMvc.perform(put("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\": \"12345\"}"))
                .andExpect(status().isNotFound());
    }

    // Teste de exclusão de pedido
    @Test
    void testExcluirPedido_Success() throws Exception {
        String codigoPedido = "12345";
        doNothing().when(pedidoUseCaseFacade).deletarPedido(codigoPedido);

        mockMvc.perform(delete("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testExcluirPedido_NotFound() throws Exception {
        String codigoPedido = "12345";
        doThrow(new RuntimeException("Pedido não encontrado")).when(pedidoUseCaseFacade).deletarPedido(codigoPedido);

        mockMvc.perform(delete("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Teste de erro genérico
    @Test
    void testErroGenerico() throws Exception {
        String codigoPedido = "12345";
        when(pedidoUseCaseFacade.getPedidoPorCodigo(codigoPedido)).thenThrow(new RuntimeException("Erro inesperado"));

        mockMvc.perform(get("/pedidos/{codigoPedido}", codigoPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

     */
}

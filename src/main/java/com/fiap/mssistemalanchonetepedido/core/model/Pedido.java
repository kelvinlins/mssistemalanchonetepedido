package com.fiap.mssistemalanchonetepedido.core.model;

import com.fiap.mssistemalanchonetepedido.core.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String codigo;
    private Cliente cliente;
    private List<PedidoItem> pedidoItens = new ArrayList<>();
    private BigDecimal desconto;
    private StatusPedidoEnum status;
    private LocalDateTime horaCheckout;

    public String getTempoEspera(){
        if (Objects.isNull(horaCheckout)){
            return "0";
        }
        var tempoEsperada = Duration.between(horaCheckout, LocalDateTime.now());
        return String.valueOf(tempoEsperada.toMinutes())+" min";
    }

    public String getCodigoClienteAsString(){
        return Objects.nonNull(cliente)?
          cliente.getCodigoAsString() :
          null;
    }
}

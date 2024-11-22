package co.vinni.cqrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoDatos {

    private Long usuarioId;
    private Long restauranteId;
    private Long domiciliarioId;
    private String detallePedido;

}
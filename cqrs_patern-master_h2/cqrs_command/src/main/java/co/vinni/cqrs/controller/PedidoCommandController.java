package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.PedidoDatos;
import co.vinni.cqrs.persistence.entity.Pedido;
import co.vinni.cqrs.service.PedidoCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoCommandController {
    private final PedidoCommandService pedidoCommandService;

    @PostMapping("/")
    public Pedido createPedido(@RequestBody PedidoDatos PedidoDatos) {
        return pedidoCommandService.createPedido(PedidoDatos);
    }

    @PutMapping("/{pedidoId}/restaurante/{restauranteId}")
    public Pedido actualizarEstadoRestaurante(@PathVariable Long pedidoId,
                                              @PathVariable Long restauranteId,
                                              @RequestParam String estado) {
        return pedidoCommandService.actualizarEstadoRestaurante(pedidoId, restauranteId, estado);
    }

    @PutMapping("/{pedidoId}/domiciliario/{domiciliarioId}")
    public Pedido actualizarEstadoDomiciliario(@PathVariable Long pedidoId,
                                               @PathVariable Long domiciliarioId,
                                               @RequestParam String estado) {
        return pedidoCommandService.actualizarEstadoDomiciliario(pedidoId, domiciliarioId, estado);
    }
}

package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Pedido;
import co.vinni.cqrs.service.PedidoQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoQueryController {
    private final PedidoQueryService pedidoQueryService;

    @GetMapping("/")
    public List<Pedido> obtenerAll() {
        return this.pedidoQueryService.getAll();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable String id) {
        return this.pedidoQueryService.getById(id);
    }
}

package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Restaurante;
import co.vinni.cqrs.service.RestauranteQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RestauranteQueryController {
    private final RestauranteQueryService restauranteQueryService;

    @GetMapping("/")
    public List<Restaurante> obtenerAll() {
        return this.restauranteQueryService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurante obtenerPorId(@PathVariable String id) {
        return this.restauranteQueryService.getById(id);
    }
}

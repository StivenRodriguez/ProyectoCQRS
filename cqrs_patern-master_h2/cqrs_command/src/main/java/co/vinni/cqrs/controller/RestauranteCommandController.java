package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.RestauranteEvent;
import co.vinni.cqrs.persistence.entity.Restaurante;
import co.vinni.cqrs.service.RestauranteCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes")
@AllArgsConstructor
public class RestauranteCommandController {

    private final RestauranteCommandService restauranteCommandService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante createRestaurante(@RequestBody RestauranteEvent restauranteEvent) {
        return restauranteCommandService.createRestaurante(restauranteEvent);
    }

    @PutMapping("/")
    public Restaurante updateRestaurante(@RequestBody RestauranteEvent restauranteEvent) {
        return restauranteCommandService.updateRestaurante(restauranteEvent);
    }
}

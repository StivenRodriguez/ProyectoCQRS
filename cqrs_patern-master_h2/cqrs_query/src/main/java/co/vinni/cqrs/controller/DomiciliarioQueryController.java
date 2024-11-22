package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Domiciliario;
import co.vinni.cqrs.service.DomiciliarioQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/domiciliarios")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DomiciliarioQueryController {
    private final DomiciliarioQueryService domiciliarioQueryService;

    @GetMapping("/")
    public List<Domiciliario> obtenerAll() {
        return this.domiciliarioQueryService.getAll();
    }

    @GetMapping("/{id}")
    public Domiciliario obtenerPorId(@PathVariable String id) {
        return this.domiciliarioQueryService.getById(id);
    }
}

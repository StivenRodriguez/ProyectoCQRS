package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.DomiciliarioEvent;
import co.vinni.cqrs.persistence.entity.Domiciliario;
import co.vinni.cqrs.service.DomiciliarioCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domiciliarios")
@AllArgsConstructor
public class DomiciliarioCommandController {

    private final DomiciliarioCommandService domiciliarioCommandService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Domiciliario createDomiciliario(@RequestBody DomiciliarioEvent domiciliarioEvent) {
        return domiciliarioCommandService.createDomiciliario(domiciliarioEvent);
    }

    @PutMapping("/")
    public Domiciliario updateDomiciliario(@RequestBody DomiciliarioEvent domiciliarioEvent) {
        return domiciliarioCommandService.updateDomiciliario(domiciliarioEvent);
    }
}

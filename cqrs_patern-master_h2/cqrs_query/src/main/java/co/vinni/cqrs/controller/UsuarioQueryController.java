package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Usuario;
import co.vinni.cqrs.service.UsuarioQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioQueryController {
    private final UsuarioQueryService usuarioQueryService;

    @GetMapping("/")
    public List<Usuario> obtenerAll() {
        return this.usuarioQueryService.getAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable String id) {
        return this.usuarioQueryService.getById(id);
    }
}

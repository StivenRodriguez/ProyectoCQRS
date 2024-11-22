package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.UsuarioEvent;
import co.vinni.cqrs.persistence.entity.Usuario;
import co.vinni.cqrs.service.UsuarioCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioCommandController {

    private final UsuarioCommandService usuarioCommandService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUsuario(@RequestBody UsuarioEvent usuarioEvent) {
        return usuarioCommandService.createUsuario(usuarioEvent);
    }

    @PutMapping("/")
    public Usuario updateUsuario(@RequestBody UsuarioEvent usuarioEvent) {
        return usuarioCommandService.updateUsuario(usuarioEvent);
    }
}

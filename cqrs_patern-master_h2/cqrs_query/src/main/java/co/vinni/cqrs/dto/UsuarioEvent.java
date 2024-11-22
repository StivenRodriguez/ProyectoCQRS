package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEvent {
    private String eventType;
    private Usuario usuario;
}

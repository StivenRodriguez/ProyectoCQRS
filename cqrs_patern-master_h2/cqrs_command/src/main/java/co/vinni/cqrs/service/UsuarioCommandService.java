package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.UsuarioEvent;
import co.vinni.cqrs.persistence.entity.Usuario;
import co.vinni.cqrs.persistence.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioCommandService {

    private final UsuarioRepository usuarioRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Usuario createUsuario(UsuarioEvent usuarioEvent) {
        Usuario usuario = usuarioRepository.save(usuarioEvent.getUsuario());
        UsuarioEvent event = new UsuarioEvent("CreateUsuario", usuario);
        kafkaTemplate.send("usuario-event-topic", event);
        return usuario;
    }

    public Usuario updateUsuario(UsuarioEvent usuarioEvent) {
        Optional<Usuario> existingUsuarioOpt = usuarioRepository.findById(usuarioEvent.getUsuario().getId());
        if (existingUsuarioOpt.isPresent()) {
            Usuario existingUsuario = existingUsuarioOpt.get();
            Usuario newUsuario = usuarioEvent.getUsuario();
            existingUsuario.setNombre(newUsuario.getNombre());
            existingUsuario.setEmail(newUsuario.getEmail());
            existingUsuario.setTelefono(newUsuario.getTelefono());
            Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
            UsuarioEvent event = new UsuarioEvent("UpdateUsuario", updatedUsuario);
            kafkaTemplate.send("usuario-event-topic", event);
            return updatedUsuario;
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}

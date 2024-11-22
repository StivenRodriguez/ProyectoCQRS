package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.UsuarioEvent;
import co.vinni.cqrs.persistence.entity.Usuario;
import co.vinni.cqrs.persistence.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioQueryService {
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll();
    }

    public Usuario getById(String id) {return this.usuarioRepository.findById(id).get(); }

    @KafkaListener(topics = "usuario-event-topic",groupId = "usuario-event-group")
    public void processUsuarioEvents(UsuarioEvent usuarioEvent){
        Usuario usuario = usuarioEvent.getUsuario();
        if(usuarioEvent.getEventType().equals("CreateUsuario")){
            usuarioRepository.save(usuario);
        }

        if(usuarioEvent.getEventType().equals("UpdateUsuario")){
            Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).get();
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setTelefono(usuario.getTelefono());
            existingUsuario.setEmail(usuario.getEmail());
            usuarioRepository.save(usuario);
        }
    }
}

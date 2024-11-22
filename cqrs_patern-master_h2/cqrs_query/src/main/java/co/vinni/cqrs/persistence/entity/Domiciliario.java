package co.vinni.cqrs.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "domiciliarios")
public class Domiciliario {
    @Id
    private String id;
    private String nombre;
    private String telefono;
}
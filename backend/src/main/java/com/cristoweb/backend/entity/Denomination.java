package com.cristoweb.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "denominations")
@Data                   // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor      // Constructor vacío
@AllArgsConstructor     // Constructor con todos los campos
@Builder                // Builder pattern (opcional)
public class Denomination {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}

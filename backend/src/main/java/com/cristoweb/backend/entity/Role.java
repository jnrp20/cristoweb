package com.cristoweb.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data                   // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor      // Constructor vacío
@AllArgsConstructor     // Constructor con todos los campos
@Builder                // Builder pattern (opcional)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol_name", nullable = false, unique = true, length = 50)
    private String roleName;

    @Column(name = "rol_description")
    private String roleDescription;
}

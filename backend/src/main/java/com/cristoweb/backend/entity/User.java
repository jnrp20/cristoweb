package com.cristoweb.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //“Deja que la base de datos se encargue de generar el valor del ID automáticamente.”
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "password", nullable = false, unique = false, length = 255)
    private String password;

    @Column(name = "full_name", nullable = false, unique = false, length=150)
    private String fullName;

    @Column(name = "country", nullable = false, unique = false, length=3)
    private String country;


    @ManyToOne(fetch = FetchType.LAZY) // Relación muchos-a-uno: muchos usuarios pueden tener un mismo rol; // LAZY indica que el rol se cargará solo cuando se acceda a él.
    @JoinColumn(name = "role_id") // Especifica la columna en la tabla "users" que actúa como clave foránea para la relación con la tabla "roles".
    //nota para role_id. esta columna almacena la llave foranea que referencia al id del rol en la tabla roles.
    private Role role; // Asociación con la entidad Role, si por ejemplo Denominations, entonces haria el cruce con Demonitanion y no con Role.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denomination_id")
    private Denomination denomination;

    @Column(name ="created_at")
    private LocalDateTime createdAt;

}

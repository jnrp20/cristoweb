package com.cristoweb.backend.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "contents")
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
public class Content {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) //identity para que la base de datos gestione el id
private Long id;

@Column(name="title", nullable = false, length = 200, unique = true)
private String title;

@Column(name= "slug", nullable = false, unique=true, length=250)
private String slug;

@Column(name="body", columnDefinition = "TEXT" , nullable = false) // Usamos columnDefinition para definir el tipo de columna como TEXT en la base de datos.
private String body;

@Column(name = "created_at" ,updatable = false) //updatable false para que no se pueda modificar despues de la creacion
private LocalDateTime createdAt;

@Column(name = "updated_at")
private LocalDateTime updatedAt;


@ManyToOne(fetch= FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "author_id")
private User author;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "denomination")
private Denomination denomination;

}

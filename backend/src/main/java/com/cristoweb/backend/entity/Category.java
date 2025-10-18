package com.cristoweb.backend.entity;

import jakarta.persistence.*; // Importamos todas las anotaciones de JPA necesarias. entre ellas @Entity, @Table, @Id, @GeneratedValue, @Column, @ManyToOne, @JoinColumn
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", nullable = false, unique = true, length = 100)
    private String name;


    @Column(name ="description", columnDefinition = "TEXT") // Usamos columnDefinition para definir el tipo de columna como TEXT en la base de datos.
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) //lazy para cargar solo cuando se necesite
    @JoinColumn(name = "denomination")
    private Denomination denomination;
   
}

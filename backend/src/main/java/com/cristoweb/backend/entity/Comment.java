package com.cristoweb.backend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Llave primaria autoincremental

    @Column(name = "body", columnDefinition = "TEXT", nullable = false)
    private String body; // Texto del comentario

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id") // Relación con la tabla 'contents'
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Relación con la tabla 'users'
    private User user;

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; // Fecha de creación del comentario
}

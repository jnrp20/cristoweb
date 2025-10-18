package com.cristoweb.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> { //"Crea un repositorio JPA para manejar los datos de la entidad Comment, cuyo ID es de tipo Long."

    // 🟢 Obtener todos los comentarios de un contenido específico
    List<Comment> findByContentId(Long contentId);

    // 🟢 Obtener todos los comentarios de un usuario específico
    List<Comment> findByUserId(Long userId);

    // 🟢 Obtener todos los comentarios de un usuario en un contenido específico
    List<Comment> findByUserIdAndContentId(Long userId, Long contentId);

    // 🟢 Buscar comentarios por texto (parcial, ignorando mayúsculas)
    List<Comment> findByTextContainingIgnoreCase(String keyword);

    // 🟢 Ordenar los comentarios por fecha descendente
    List<Comment> findByContentIdOrderByCreatedAtDesc(Long contentId);
    
}

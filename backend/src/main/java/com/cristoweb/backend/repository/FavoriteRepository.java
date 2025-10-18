package com.cristoweb.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    List<Favorite> findByUserId(Long userId); // Método para obtener todos los favoritos de un usuario específico

    List<Favorite> findByContentId(Long contentId); // Método para obtener todos los favoritos de un contenido específico

    Optional<Favorite> findByUserIdAndContentId(Long userId, Long contentId); // Método para verificar si un usuario ha marcado un contenido como favorito
//es opcional porque puede que no exista ese favorito

    void deleteByUserIdAndContentId(Long userId, Long contentId); // Método para eliminar un favorito específico por ejemplo, cuando el usuario quita un “like”).


}

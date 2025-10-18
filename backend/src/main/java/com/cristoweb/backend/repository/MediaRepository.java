package com.cristoweb.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    
List<Media> findByUploadedById(Long userId); // Método para obtener todos los medios subidos por un usuario específico

List<Media> findByType(String type); // Método para obtener todos los medios de un tipo específico (por ejemplo, imagen, video)


Media findByName(String fileName); // Método para buscar medios por nombre de archivo exact. no se usa List porque solo puede haber uno con ese nombre y no varios

Media findByUrl(String url); // Método para buscar medios por URL exacta. no se usa List porque solo puede haber uno con esa url y no varios

}

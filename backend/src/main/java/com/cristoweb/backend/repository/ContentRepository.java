package com.cristoweb.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {


List<Content> findByCategoryId(Long categoryId);// Filtrar por categoria


List<Content>findByAuthorId(Long authorId);// Filtrar por autor

List<Content> findByDenominationId(Long denominationId);// Filtrar por denominacion


List<Content> findByCategoryIdAndDenominationId(Long categoryId, Long denominationId); // Filtrar por categoria y denominacion

// si se deseapor titulo exacto 
Content findByTitleContainingIgnoreCase(String title); //containingIgnoreCase es para buscar sin importar mayusculas o minusculas

//si se desea busacar por titulo parcial
List<Content> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title); //buscar por titulo parcial y ordenar por fecha de creacion descendente

boolean existsByTitle(String title);






}

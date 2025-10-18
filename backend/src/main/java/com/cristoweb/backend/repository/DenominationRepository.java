package com.cristoweb.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Denomination;

@Repository
public interface DenominationRepository extends JpaRepository<Denomination, Long> {
    
    List<Denomination> findAll(); // Obtener todas las denominaciones

    Denomination findById(long id);// Obtener una denominacion por su ID

    Denomination findByName(String name);// Obtener una denominacion por su nombre

    List<Denomination> findByNameContainingIgnoreCase(String name); // Buscar denominaciones por nombre parcial, ignorando mayusculas/minusculas

    void deleteById(long id);// Eliminar una denominacion por su ID




}

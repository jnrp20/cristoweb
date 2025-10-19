package com.cristoweb.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
Role findbyId(Long id);// Método para encontrar un rol por su ID

List<Role> findAll();// Método para obtener todos los roles

Optional<Role> findByRolNameIgnoreCaseRole(String rolName); // Método para encontrar un rol por su nombre ignorando mayúsculas y minúsculas

Optional<Role> findByRoleName(String rolName);// Método para encontrar un rol por su nombre parcialmente

boolean existsByName(String roleName); // Método para verificar si un rol existe por su nombre
boolean existsById(Long id); // Método para verificar si un rol existe por su ID

}

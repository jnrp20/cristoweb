package com.cristoweb.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


Optional<User> findByEmail(String email);// Método para encontrar un usuario por su correo electrónico. es optional porque puede que no exista.

Optional<User> findByUsername(String username);// Método para encontrar un usuario por su nombre de usuario. Es optional porque puede que no exista.

List<User> findByCountry(String country);// Método para encontrar usuarios por su país.

List<User> findByRoleId(Long roleId);// Método para encontrar usuarios por el ID de su rol.

List<User> findByDenominatioId(Long denominationId);// Método para encontrar usuarios por el ID de su demonitanion.

List<User> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);// Método para encontrar usuarios creados entre dos fechas.

List<User> findByFullName(String fullName);// Método para encontrar usuarios por su nombre completo.

boolean existsByEmail(String email);// Método para verificar si existe un usuario con un correo electrónico específico.
  // ===========================================================
    // NOTA: Todos los métodos CRUD básicos ya vienen heredados
    // de JpaRepository, por lo que NO es necesario agregarlos
    // explícitamente a menos que se quiera documentar.
    // ===========================================================

    // ---------- CREATE / UPDATE ----------
    // User save(User entity);
    // - Guarda un nuevo usuario o actualiza uno existente.
    // - Si la entidad tiene id=null => inserta un nuevo registro.
    // - Si la entidad tiene id existente => actualiza el registro.
    // Ejemplo:
    // User saved = userRepository.save(newUser);

    // ---------- READ ----------
    // Optional<User> findById(Long id);
    // - Busca un usuario por su ID (llave primaria).
    // - Devuelve Optional<User> porque puede no existir.
    // Ejemplo:
    // Optional<User> user = userRepository.findById(5L);

    // List<User> findAll();
    // - Devuelve todos los usuarios de la base de datos.
    // Ejemplo:
    // List<User> allUsers = userRepository.findAll();

    // long count();
    // - Devuelve el número total de registros en la tabla.
    // Ejemplo:
    // long total = userRepository.count();

    // ---------- DELETE ----------
    // void deleteById(Long id);
    // - Elimina un usuario por su ID.
    // - No es necesario declararlo, ya viene heredado.
    // Ejemplo:
    // userRepository.deleteById(5L);

    // void delete(User entity);
    // - Elimina la entidad especificada.
    // Ejemplo:
    // userRepository.delete(user);

    // void deleteAll();
    // - Elimina todos los registros de la tabla.
    // Ejemplo:
    // userRepository.deleteAll();

    // void deleteAllInBatch();
    // - Borra todos los registros en una sola operación SQL (rápido, pero sin checks de seguridad)

}

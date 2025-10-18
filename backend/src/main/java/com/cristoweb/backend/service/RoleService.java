package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Role;
import com.cristoweb.backend.repository.RoleRepository;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

      // esto es un constructor para inyectar la dependencia del UserRepository en el
    // UserService.
    // de esta manera, el UserService puede utilizar los métodos definidos en el
    // UserRepository para interactuar con la base de datos.
      public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



        // Obtener rol por ID
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

// Obtener todos los roles
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    };

    // Crear un rol
    public Role createRole(Role role) {
        if (roleRepository.existsByName(role.getRoleName())) {
            throw new RuntimeException("Rol ya existe");
        }
        return roleRepository.save(role);
    }

      // Obtener rol por nombre
    public Role getRoleByName(String name) {
        return roleRepository.findByRoleName(name).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    
    
}

}

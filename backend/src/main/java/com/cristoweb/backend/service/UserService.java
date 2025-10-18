package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.User;
import com.cristoweb.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    // esto es un constructor para inyectar la dependencia del UserRepository en el
    // UserService.
    // de esta manera, el UserService puede utilizar los métodos definidos en el
    // UserRepository para interactuar con la base de datos.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//---------------------------------------------------------Creacion-------------------------------------------------------------------------


    // Método para crear un nuevo usuario con contraseña encriptada
    public User createUser(User user) {
        // .getEmail y getPassword vienen de los getters generados por lombok en la
        // entidad User no del repo.
        if (userRepository.existsByEmail(user.getEmail())) { // exusteByEmail viene del UserRepository.
            throw new RuntimeException("Email ya registrado"); // throw new RuntimeException lanza una excepción en
                                                               // tiempo de ejecución con el mensaje "Email ya
                                                               // registrado".
            // throw new detiene la ejecución normal del programa y señala que ha ocurrido
            // un error.
        }
        user.setPassword(encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }


  //---------------------------------------------------------consulta-------------------------------------------------------------------------  

    // Método para obtener un usuario por su ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));                                                                                                         // es un

    };

    // Método para obtener un usuario por su email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    };

    // Método para obtener todos los usuarios de un país específico
    public List<User> getUserByCountry(String country) {
        // de tipo List<User> crea una variable llamada users que almacena la lista de
        // usuarios obtenida del repositorio.
        List<User> users = userRepository.findByCountry(country);
        if (users.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios en el país especificado");
        }
        return users; // users es la lista de usuarios obtenida del repositorio.
    };

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll(); // findAll() es un método proporcionado por JpaRepository que devuelve una lista de todas las entidades User almacenadas en la base de datos.
    };

    // Método para obtener usuarios por el ID de su rol
    public List<User> getUsersByRoleId(Long roleId) {
        return userRepository.findByRoleId(roleId);
    };

    // Método para obtener usuarios por el ID de su denominación
    public List<User> getUsersByDenominationId(Long denominationId) {
        return userRepository.findByDenominatioId(denominationId);
    };

    // Método para obtener usuarios creados entre dos fechas
    public List<User> getUsersCreatedBetween(java.time.LocalDateTime start, java.time.LocalDateTime end) {
        return userRepository.findByCreatedAtBetween(start, end);
    };

    // Método para obtener usuarios por su nombre completo
    public List<User> getUsersByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    };


  //---------------------------------------------------------Actualizacion-------------------------------------------------------------------------  

public User updateUser(Long id, User userData) {
    User user = getUserById(id); //User es la entidad y user es la variable que almacena el usuario obtenido por su ID.

    if (userData.getFullName() != null) user.setFullName(userData.getFullName());
    if (userData.getCountry() != null) user.setCountry(userData.getCountry());

    // Actualizar email
    if (userData.getEmail() != null) {
        if (!userData.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(userData.getEmail())) {//Si el email nuevo NO es igual al email actual
            throw new RuntimeException("Email ya registrado por otro usuario");
        }
        user.setEmail(userData.getEmail());
    }

    // Actualizar contraseña si se incluye
    if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
        user.setPassword(encryptPassword(userData.getPassword()));
    }

    return userRepository.save(user);
}


//---------------------------------------------------------Eliminacion-------------------------------------------------------------------------

// Método para eliminar un usuario por su ID
public void deleteUser(Long id) {
    getUserById(id); // valida existencia
    userRepository.deleteById(id); // JPA
}


//---------------------------------------------------------Utilidades-------------------------------------------------------------------------


    // Método privado para encriptar la contraseña utilizando BCrypt
    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password); // devuelve la contraseña encriptada
    };


//Metodo para login de usuario
    public User login(String email, String password){

        User user = getUserByEmail(email); // obtiene el usuario por su email

         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      if (!encoder.matches(password, user.getPassword())) {// matches compara la contraseña sin encriptar con la encriptada
        throw new RuntimeException("Contraseña incorrecta");
         }

        return user;
    };





}

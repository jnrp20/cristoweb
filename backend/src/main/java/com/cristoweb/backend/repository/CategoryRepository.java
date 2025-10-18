package com.cristoweb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristoweb.backend.entity.Category;


@Repository //esta anotacion le dice a spring “Esta interfaz forma parte de la capa de acceso a datos.”
public interface CategoryRepository extends JpaRepository<Category, Long> { //Crea un repositorio para la entidad Category que tiene un ID de tipo Long.
    //metodos pertenecientes a jpa repository: 
    //findAll, findById, save, deleteById, etc.
    
    boolean existsByName(String name); //metodo personalizado para verificar si existe una categoria por su nombre.
                                        //el name sale del atributo name de la entidad Category.
                                        //equivalente a "SELECT COUNT(*) > 0 FROM categories WHERE name = ?" que significa que devuelve true si existe al menos una categoria con ese nombre.


}

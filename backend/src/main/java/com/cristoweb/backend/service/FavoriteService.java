package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Favorite;
import com.cristoweb.backend.repository.FavoriteRepository;

@Service
public class FavoriteService {
    
private final FavoriteRepository favoriteRepository;


public FavoriteService(FavoriteRepository favoriteRepository){

    this.favoriteRepository = favoriteRepository;
};


public Favorite addFavorite(Favorite favorite) {
    // Verifica si ya existe el favorito
    boolean exists = favoriteRepository.findByUserIdAndContentId(
        favorite.getUser().getId(),
        favorite.getContent().getId()
    ).isPresent(); // .isPresent() es de Optional (viene del repo)

    if (exists) { //recordar que exist es booleano
        throw new RuntimeException("Este contenido ya está marcado como favorito");
    }

    return favoriteRepository.save(favorite); // save() viene de JPARepository
}


public List<Favorite> getFavoritesByUser(Long userId){

List<Favorite> favorateList = favoriteRepository.findByUserId(userId);

if (favorateList.isEmpty()) {
    throw new RuntimeException("El usuario no tiene contenidos marcados como favoritos");
}


return favorateList;
    
};


public void removeFavorite(Long userId, Long contentId) {
    favoriteRepository.deleteByUserIdAndContentId(userId, contentId);
}


//por si el cliente elimina su cuenta 
//borra todos los favoritos
public void removeAllFavoritesByUser(Long userId) {
    List<Favorite> favorites = favoriteRepository.findByUserId(userId);
    if (favorites.isEmpty()) {
        throw new RuntimeException("El usuario no tiene favoritos para eliminar");
    }
    favoriteRepository.deleteAll(favorites);
}

}

package com.cristoweb.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Media;
import com.cristoweb.backend.repository.MediaRepository;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    // 🔹 Crear media
    public Media createMedia(Media media) {
        if (mediaRepository.findByName(media.getFileName()) != null) {
            throw new RuntimeException("Ya existe un archivo con ese nombre");
        }
        if (mediaRepository.findByUrl(media.getUrl()) != null) {
            throw new RuntimeException("Ya existe un archivo con esa URL");
        }
        media.setUploadedAt(LocalDateTime.now());
        return mediaRepository.save(media);
    }

    // 🔹 Obtener media por ID
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    // 🔹 Obtener todas las medias
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    // 🔹 Obtener media subida por un usuario
    public List<Media> getMediaByUser(Long userId) {
        List<Media> mediaList = mediaRepository.findByUploadedById(userId);
        if (mediaList.isEmpty()) {
            throw new RuntimeException("El usuario no tiene medios subidos");
        }
        return mediaList;
    }

    // 🔹 Obtener media por tipo (imagen, video, etc.)
    public List<Media> getMediaByType(String type) {
        return mediaRepository.findByType(type);
    }

    // 🔹 Obtener media por nombre exacto
    public Media getMediaByName(String name) {
        Media media = mediaRepository.findByName(name);
        if (media == null) {
            throw new RuntimeException("No existe archivo con ese nombre");
        }
        return media;
    }

    // 🔹 Obtener media por URL exacta
    public Media getMediaByUrl(String url) {
        Media media = mediaRepository.findByUrl(url);
        if (media == null) {
            throw new RuntimeException("No existe archivo con esa URL");
        }
        return media;
    }

    // 🔹 Actualizar información de una media
    public Media updateMedia(Long id, Media mediaData) {
        Media media = getMediaById(id);

        if (mediaData.getFileName() != null) {
            media.setFileName(mediaData.getFileName());
        }
        if (mediaData.getType() != null) {
            media.setType(mediaData.getType());
        }
        if (mediaData.getUrl() != null) {
            media.setUrl(mediaData.getUrl());
        }

      
        return mediaRepository.save(media);
    }

    // 🔹 Eliminar media por ID
    public void deleteMedia(Long id) {
        if (!mediaRepository.existsById(id)) {
            throw new RuntimeException("No existe media con ID: " + id);
        }
        mediaRepository.deleteById(id);
    }
}

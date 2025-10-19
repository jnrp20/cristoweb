package com.cristoweb.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Content;
import com.cristoweb.backend.repository.ContentRepository;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    };

    //optener por id
    public Content getcontentById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
    };

    //obtener todo
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    };

    public List<Content> getByAuthor(Long authorId){

        return contentRepository.findByAuthorId(authorId);

    };


    public List<Content> getByCategory(Long categoryId){

        return contentRepository.findByCategoryId(categoryId);

    };


    public List<Content> getByDenomination(Long denominationId){

        return contentRepository.findByDenominationId(denominationId);

    };
public List<Content> getByTitle(String title) {
    return contentRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(title);
}




    public Content updateContent(Long id, Content contentData){

        Content content = getcontentById(id);

        if (contentData.getCategory() != null) {
            content.setCategory(contentData.getCategory());
        }
        if (contentData.getDenomination() != null) {
            content.setDenomination(contentData.getDenomination());
        
        }

        if (contentData.getTitle()!=null) {
            content.setTitle(contentData.getTitle());
        }
        
        if (contentData.getSlug()!=null) {
            content.setSlug(contentData.getSlug());
        }
        if (contentData.getBody()!=null) {
            content.setBody(contentData.getBody());
        }



            content.setUpdatedAt(LocalDateTime.now());

        return contentRepository.save(content);

    };

//borrar por id
    public void deleteContent(Long id){
         if (!contentRepository.existsById(id)) {
            throw new RuntimeException("No existe contenido con ID: " + id);
        }
        contentRepository.deleteById(id);
    };


}

package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Comment;
import com.cristoweb.backend.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){

        this.commentRepository = commentRepository;

    };


    public Comment createComment(Comment comment){


        return commentRepository.save(comment); 
        
    };

      // 🟢 Obtener comentarios de un contenido específico
    public List<Comment> getCommentsByContentId(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }


      // 🟢 Obtener comentarios de un usuario en un contenido
    public List<Comment> getCommentsByUserAndContent(Long userId, Long contentId) {
        return commentRepository.findByUserIdAndContentId(userId, contentId);
    }

    public Comment updateComment(Long id, Comment commentData){

        Comment comment = commentRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe el comentario")); //busca el comentario a editar

        if (commentData.getBody()!=null) {
            comment.setBody(commentData.getBody());            
        }

        return commentRepository.save(comment);

    };

    public void deleteComment(Long id){
        
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("No existe el comentario a eliminar");
        }
        commentRepository.deleteById(id);
    };


}

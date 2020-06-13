package com.redesocial.ppads.controller;

import com.redesocial.ppads.entity.Post;
import com.redesocial.ppads.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/redesocial/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/listar")
    public List<Post> listaPost(){
        return postService.readAll();
    }

    @GetMapping("/listarPorId/{id}")
    public Post postPorId(@PathVariable Integer id){
        return postService.readById(id);
    }

    @GetMapping("/listarPorEmail/{email}")
    public List<Post> postsPorEmail(@PathVariable String email) { return postService.readByEmail(email); }

    @PostMapping("/criar")
    public Post criarPost(@RequestBody Post post){
        return postService.create(post);
    }

    @PutMapping("/editarPost")
    public Post updatePost(@RequestBody Post post){
        return postService.atualizaPost(post);
    }

    @DeleteMapping("/deletaPost/{idPost}")
    public boolean delelePost(@PathVariable Integer idPost){
        return postService.delete(idPost);
    }

    @GetMapping("curtir/{idPessoaCurtiu}/{idPostCurtido}")
    public Post curtir(@PathVariable Integer idPessoaCurtiu, @PathVariable Integer idPostCurtido){
        return postService.curtir(idPessoaCurtiu, idPostCurtido);
    }

    @GetMapping("undoCurtir/{idPessoaCurtiu}/{idPostCurtido}")
    public Post undoCurtir(@PathVariable Integer idPessoaCurtiu, @PathVariable Integer idPostCurtido){
        return postService.undoCurtir(idPessoaCurtiu, idPostCurtido);
    }
}

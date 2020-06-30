package com.redesocial.ppads.controller;

import com.redesocial.ppads.entity.Artigo;
import com.redesocial.ppads.entity.Post;
import com.redesocial.ppads.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/redesocial/artigo/")
public class ArtigoController {
    @Autowired
    private ArtigoService artigoService;

    @PostMapping("criaArtigo")
    public Artigo criaArtigo(@RequestBody Artigo artigo){
        return artigoService.create(artigo);
    }

    @GetMapping("listaPorEmail/{emailAutor}")
    public List<Artigo> listaPorEmail(@PathVariable String emailAutor){
        return artigoService.readAllByEmailAutor(emailAutor);
    }

    @GetMapping("procuraArtigoPorId/{idArtigo}")
    public Artigo procuraArtigoPorId(@PathVariable Integer idArtigo){
        return artigoService.readById(idArtigo);
    }

    @PutMapping("atualizaArtigo")
    public Artigo atualizaArtigo(@RequestBody Artigo artigo){
        return artigoService.update(artigo);
    }

    @DeleteMapping("deletaArtigo/{idArtigo}")
    public boolean deletaArtigo(@PathVariable Integer idArtigo){
        return artigoService.delete(idArtigo);
    }

    @GetMapping("analisaArtigo/{idArtigo}/{idPessoa}")
    public boolean analisaPost(@PathVariable Integer idArtigo, @PathVariable Integer idPessoa){
        return artigoService.analisaArtigo(idArtigo, idPessoa);
    }

    @GetMapping("curtir/{idPessoaCurtiu}/{idArtigoCurtido}")
    public Artigo curtir(@PathVariable Integer idPessoaCurtiu, @PathVariable Integer idArtigoCurtido){
        return artigoService.curtir(idPessoaCurtiu, idArtigoCurtido);
    }

    @GetMapping("undoCurtir/{idPessoaCurtiu}/{idArtigoCurtido}")
    public Artigo undoCurtir(@PathVariable Integer idPessoaCurtiu, @PathVariable Integer idArtigoCurtido){
        return artigoService.undoCurtir(idPessoaCurtiu, idArtigoCurtido);
    }

}

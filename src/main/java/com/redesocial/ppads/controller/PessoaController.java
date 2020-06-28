package com.redesocial.ppads.controller;

import com.redesocial.ppads.entity.Pessoa;
import com.redesocial.ppads.model.Notificacao;
import com.redesocial.ppads.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redesocial/pessoa")
@CrossOrigin("*")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/login")
    public Pessoa login(@RequestBody Pessoa pessoa){
        return pessoaService.login(pessoa);
    }

    @PostMapping("/cadastro")
    public Pessoa cadastroPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.create(pessoa);
    }

    @GetMapping("/listar")
    public List<Pessoa> listaPessoa(){
        return pessoaService.readAll();
    }

    @GetMapping("/listarExceto/{email}")
    public List<Pessoa> listaExceto(@PathVariable String email){
        return pessoaService.readAllExceptEmail(email);
    }

    @GetMapping("/buscarPorId/{id}")
    public Pessoa buscaPessoaPorId(@PathVariable Integer id){
        return pessoaService.readById(id);
    }

    @GetMapping("/emailpessoa/{email}")
    public Pessoa buscaPessoaPorEmail(@PathVariable String email) { return pessoaService.readByEmail(email); }

    @PutMapping("/atualizar")
    public Pessoa atualizarPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.update(pessoa);
    }

    @DeleteMapping("/deletar/{id}")
    public boolean deletarPessoa(@PathVariable Integer id){
        return pessoaService.delete(id);
    }

    @GetMapping("/seguindo/{id}")
    public List<Pessoa> seguindo(@PathVariable Integer id) {
        return pessoaService.findSeguindo(id);
    }

    @GetMapping("/seguidores/{id}")
    public List<Pessoa> seguidores(@PathVariable Integer id) {
        return pessoaService.findSeguidores(id);
    }

    @GetMapping("/verificaFollow/{id}/{idASeguir}")
    public boolean verificaFollow(@PathVariable Integer id, @PathVariable Integer idASeguir){
        return pessoaService.verificaFollow(id, idASeguir);
    }

    @GetMapping("/follow/{id}/{idSeguir}")
    public List<Pessoa> follow(@PathVariable Integer id, @PathVariable Integer idSeguir) {
        return pessoaService.followPessoa(id, idSeguir);
    }

    @GetMapping("/unfollow/{id}/{idDeixarDeSeguir}")
    public List<Pessoa> unfollow(@PathVariable Integer id, @PathVariable Integer idDeixarDeSeguir) {
        return pessoaService.undoFollowPessoa(id, idDeixarDeSeguir);
    }

    @GetMapping("/notificacao/{email}")
    public List<Notificacao> getAllNotificacao(@PathVariable String email) {
        return pessoaService.getAllNotificacao(email);
    }


}

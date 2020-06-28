package com.redesocial.ppads.service;

import com.redesocial.ppads.entity.Artigo;
import com.redesocial.ppads.entity.Pessoa;
import com.redesocial.ppads.model.Notificacao;
import com.redesocial.ppads.repository.ArtigoRepository;
import com.redesocial.ppads.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtigoService {
    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Artigo create(Artigo artigo){
        Pessoa pessoa = pessoaRepository.findByEmail(artigo.getEmailAutor());
        List<Integer> seguidores = pessoa.getSeguidores();
        Artigo artigoSalvo = artigoRepository.save(artigo);

        for (int i = 0; i < seguidores.size(); i++) {
            Notificacao notificacao = new Notificacao();
            Pessoa aux = pessoaRepository.findById(seguidores.get(i)).get();

            notificacao.setIdPublicacao(artigoSalvo.getId());
            notificacao.setVisualizacao(false);
            notificacao.setTipoPublicacao("Artigo");
            notificacao.setAutor(pessoa.getInfos().getNomePessoa());
            notificacao.setTitulo(artigo.getPublicacao().getTitulo().substring(0,16) + "...");

            aux.getListaDeNotificacao().add(notificacao);

            pessoaRepository.save(aux);
        }
        return artigoSalvo;
    }

    public List<Artigo> readAllByEmailAutor(String emailAutor){
        return artigoRepository.findByEmailAutor(emailAutor);
    }

    public Artigo readById(Integer id){
        return artigoRepository.findById(id).get();
    }

    public Artigo update(Artigo artigo){
        if (artigoRepository.existsById(artigo.getId())){
            Artigo _artigo = artigoRepository.findById(artigo.getId()).get();
            _artigo.setPublicacao(artigo.getPublicacao());

            return artigoRepository.save(_artigo);
        }
        return artigoRepository.save(artigo);
    }

    public boolean delete(Integer id){
        if(artigoRepository.existsById(id)){
            artigoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

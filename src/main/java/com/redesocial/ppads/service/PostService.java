package com.redesocial.ppads.service;

import com.redesocial.ppads.entity.Pessoa;
import com.redesocial.ppads.entity.Post;
import com.redesocial.ppads.model.Notificacao;
import com.redesocial.ppads.repository.PessoaRepository;
import com.redesocial.ppads.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Post create(Post post){
        Pessoa pessoa = pessoaRepository.findByEmail(post.getEmailAutor());
        List<Integer> seguidores = pessoa.getSeguidores();
        Post postSalvo = postRepository.save(post);

        for (int i = 0; i < seguidores.size(); i++) {
            Notificacao notificacao = new Notificacao();
            Pessoa aux = pessoaRepository.findById(seguidores.get(i)).get();

            notificacao.setIdPublicacao(postSalvo.getId());
            notificacao.setVisualizacao(false);
            notificacao.setTipoPublicacao("Post");
            aux.getListaDeNotificacao().add(notificacao);

            pessoaRepository.save(aux);
        }

        return postSalvo;
    }

    public List<Post> readAll() {
        return postRepository.findAll();
    }

    public Post readById(Integer id){
        if(postRepository.existsById(id)){
            return postRepository.findById(id).get();
        }
        return null;
    }

    public boolean delete(Integer id){
        if (postRepository.existsById(id)){
            postRepository.delete(postRepository.findById(id).get());
            return true;
        }
        return false;
    }

    public List<Post> readByEmail(String email) {
        return postRepository.findByEmailAutor(email);
    }

    public Post atualizaPost(Post post) {
        if (postRepository.existsById(post.getId())){
            Post post_update = postRepository.findById(post.getId()).get();
            post_update.setTxt(post.getTxt());
            post_update.setCurtidas(post.getCurtidas());
            return postRepository.save(post_update);
        }
        return postRepository.save(post);
    }

    public Post curtir(Integer idPessoaCurtiu, Integer idPostCurtido) {
        Pessoa pessoa = pessoaRepository.findById(idPessoaCurtiu).get();
        Post post = postRepository.findById(idPostCurtido).get();

        pessoa.getPostsCurtidos().add(idPostCurtido);

        post.getMembrosCurtiram().add(idPessoaCurtiu);
        post.setCurtidas(post.getCurtidas() + 1);

        postRepository.save(post);
        pessoaRepository.save(pessoa);

        return post;
    }

    public Post undoCurtir(Integer idPessoaCurtiu, Integer idPostCurtido){
        Pessoa pessoa = pessoaRepository.findById(idPessoaCurtiu).get();
        Post post = postRepository.findById(idPostCurtido).get();

        pessoa.getPostsCurtidos().remove(idPostCurtido);

        post.getMembrosCurtiram().remove(idPessoaCurtiu);
        post.setCurtidas(post.getCurtidas() - 1);

        postRepository.save(post);
        pessoaRepository.save(pessoa);

        return post;
    }

    public boolean analisaPost(Integer idPost, Integer idPessoa){
        if (postRepository.findById(idPost).get() == null ||
                pessoaRepository.findById(idPessoa).get() == null){
            return false;
        }
        return pessoaRepository.findById(idPessoa).get().getPostsCurtidos().contains(idPost);
    }
}

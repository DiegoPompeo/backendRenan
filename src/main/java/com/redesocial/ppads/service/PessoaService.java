package com.redesocial.ppads.service;

import com.redesocial.ppads.entity.Pessoa;
import com.redesocial.ppads.model.Notificacao;
import com.redesocial.ppads.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa login(Pessoa pessoa){
        List<Pessoa> lista = pessoaRepository.findAll();
        for (Pessoa p : lista) {
            if (p.getEmail().equals(pessoa.getEmail())
                    && p.getSenha().equals(pessoa.getSenha())) {
                return p;
            }
        }
        return null;
    }

    public Pessoa create(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> readAll(){
        return pessoaRepository.findAll();
    }

    public List<Pessoa> readAllExceptEmail(String email){
        List<Pessoa> lista = pessoaRepository.findAll();
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        for(Pessoa p: lista){
            if (p == pessoa){
                lista.remove(p);
                return lista;
            }
        }
        return null;
    }

    public Pessoa readById(Integer id){
        return pessoaRepository.findById(id).get();
    }

    public Pessoa readByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public Pessoa update(Pessoa pessoa){
        if (pessoaRepository.existsById(pessoa.getId())){
            Pessoa p = pessoaRepository.findById(pessoa.getId()).get();
            p.setEmail(pessoa.getEmail());
            p.setSenha(pessoa.getSenha());

            p.setCurriculo(pessoa.getCurriculo());
            p.setFormacao(pessoa.getFormacao());
            p.setInfos(pessoa.getInfos());
            p.setInteresses(pessoa.getInteresses());
            p.setTrabalho(pessoa.getTrabalho());
            return pessoaRepository.save(p);
        }
        return null;
    }

    public boolean delete(Integer id){
        if (pessoaRepository.existsById(id)){
            pessoaRepository.delete(pessoaRepository.findById(id).get());
            return true;
        }
        return false;
    }

    public List<Pessoa> findSeguindo(Integer id) {
        List<Integer> listaSeguindo = pessoaRepository.findById(id).get().getSeguindo();
        List<Pessoa> pessoaSeguindo = new ArrayList<>();
        for (int i = 0; i < listaSeguindo.size(); i++) {
            pessoaSeguindo.add(pessoaRepository.findById(listaSeguindo.get(i)).get());
        }
        return pessoaSeguindo;
    }

    public List<Pessoa> findSeguidores(Integer id) {
        List<Integer> listaSeguidores = pessoaRepository.findById(id).get().getSeguidores();
        List<Pessoa> pessoaSeguidores = new ArrayList<>();
        for (int i = 0; i < listaSeguidores.size(); i++) {
            pessoaSeguidores.add(pessoaRepository.findById(listaSeguidores.get(i)).get());
        }
        return pessoaSeguidores;
    }

    public boolean verificaFollow(Integer id, Integer idASeguir) {
        return pessoaRepository.findById(id).get().getSeguindo().contains(idASeguir);
    }

    public List<Pessoa> followPessoa(Integer id, Integer idSeguir) {
        List<Pessoa> lista = new ArrayList<>();
        Pessoa seguidor = pessoaRepository.findById(id).get();
        Pessoa seguindo = pessoaRepository.findById(idSeguir).get();

        if(!(seguidor.getSeguindo().contains(idSeguir))) {
            seguidor.getSeguindo().add(idSeguir);
            pessoaRepository.save(seguidor);
            lista.add(seguidor);
            seguindo.getSeguidores().add(id);
            pessoaRepository.save(seguindo);
            lista.add(seguindo);
        }

        return lista;
    }

    public List<Pessoa> undoFollowPessoa(Integer id, Integer idDeixarDeSeguir) {
        List<Pessoa> lista = new ArrayList<>();

        Pessoa seguidor = pessoaRepository.findById(id).get();
        Pessoa seguindo = pessoaRepository.findById(idDeixarDeSeguir).get();

        if(seguidor.getSeguindo().contains(idDeixarDeSeguir)) {
            if (!(seguidor.getSeguindo().isEmpty())){
                seguidor.getSeguindo().remove(idDeixarDeSeguir);
                pessoaRepository.save(seguidor);

                lista.add(seguidor);
            }

            if (!(seguindo.getSeguidores().isEmpty())){
                seguindo.getSeguidores().remove(id);
                pessoaRepository.save(seguindo);

                lista.add(seguindo);
            }
        }

        return lista;
    }

    public List<Notificacao> getAllNotificacao(String email) {
        return pessoaRepository.findByEmail(email).getListaDeNotificacao();
    }
}

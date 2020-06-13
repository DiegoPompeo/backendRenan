package com.redesocial.ppads.entity;

import com.redesocial.ppads.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    private String email;
    private String senha;

    private Curriculo curriculo;
    private Formacao formacao;
    private InfoAdicionais infos;

    private String interesses;

    private Trabalho trabalho;

    @ElementCollection
    @CollectionTable(name="segue", joinColumns=@JoinColumn(name="seguindo_id"))
    @Column(name="id_seguindo")
    private List<Integer> seguindo;

    @ElementCollection
    @CollectionTable(name="seguidor", joinColumns=@JoinColumn(name="seguidores_id"))
    @Column(name="id_seguidores")
    private List<Integer> seguidores;

    @ElementCollection
    private List<Integer> postsCurtidos;
}

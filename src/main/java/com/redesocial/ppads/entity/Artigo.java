package com.redesocial.ppads.entity;

import com.redesocial.ppads.model.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artigo {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    private Publicacao publicacao;

    private String emailAutor;

    private Integer curtidas;

    @ElementCollection
    @CollectionTable(name="curtiramArtigo", joinColumns=@JoinColumn(name="curtiram_id"))
    @Column(name="id_curtiram")
    private List<Integer> membrosCurtiram;

}

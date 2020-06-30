package com.redesocial.ppads.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Publicacao {
    private String titulo;
    private String resumo;
    private String localDaPublicacao;
    private String anoDaPublicacao;
    private String url;
    private String tags;
}

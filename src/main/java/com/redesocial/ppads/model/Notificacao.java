package com.redesocial.ppads.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Notificacao {
    private Integer idPublicacao;
    private boolean visualizacao;
    private String tipoPublicacao;
    private String autor;
    private String titulo;
}

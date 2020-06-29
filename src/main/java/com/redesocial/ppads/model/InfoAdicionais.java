package com.redesocial.ppads.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class InfoAdicionais {
    private String nomePessoa;
    private String dataNascimento;
    private String dataInicioCientista;
    private String cpf;
    private String rg;
}

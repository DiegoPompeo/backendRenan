package com.redesocial.ppads.service;

import com.redesocial.ppads.entity.Arquivo;
import com.redesocial.ppads.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository arquivoRepository;

    public Arquivo save(MultipartFile file, Integer idArtigo) throws IOException {
        Arquivo arquivo = new Arquivo();
        arquivo.setArtigoId(idArtigo);
        arquivo.setDocName(file.getOriginalFilename());
        arquivo.setFile(file.getBytes());
        arquivo.setType(file.getContentType());

        return arquivoRepository.save(arquivo);
    }
}

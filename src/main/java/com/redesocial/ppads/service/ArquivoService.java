package com.redesocial.ppads.service;

import com.redesocial.ppads.entity.Arquivo;
import com.redesocial.ppads.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArquivoService {
    @Autowired
    private ArquivoRepository arquivoRepository;

    public Arquivo save(MultipartFile file, Integer artigoId) throws IOException {

        Arquivo doc = new Arquivo();
        doc.setDocName(file.getOriginalFilename());
        doc.setFile(file.getBytes());
        doc.setArtigoId(artigoId);

        return arquivoRepository.save(doc);
    }

    public byte[] getDocumentFile(Integer id) {
        return arquivoRepository.findById(id).get().getFile();
    }

    public List findAll() {
        return arquivoRepository.findAll();
    }
}

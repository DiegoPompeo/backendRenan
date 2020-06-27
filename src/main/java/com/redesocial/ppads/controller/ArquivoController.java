package com.redesocial.ppads.controller;

import com.redesocial.ppads.entity.Arquivo;
import com.redesocial.ppads.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/redesocial/artigo/arquivo/")
public class ArquivoController {
    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("save/{id}")
    public Arquivo uploadFile(@RequestParam("myFile")MultipartFile file,
                              @PathVariable Integer id) throws IOException {
        return arquivoService.save(file, id);
    }


}

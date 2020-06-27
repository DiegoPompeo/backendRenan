package com.redesocial.ppads.controller;

import com.redesocial.ppads.entity.Arquivo;
import com.redesocial.ppads.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/redesocial/artigo/arquivo/")
public class ArquivoController {
    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("upload/{artigoId}")
    public Arquivo handleFileUpload(@RequestBody MultipartFile file,@PathVariable Integer artigoId) throws IOException {
        return arquivoService.save(file, artigoId);
    }

    @GetMapping("{id}")
    public HttpEntity getDocumentById(@PathVariable Integer id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity(arquivoService.getDocumentFile(id), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("lista")
    public @ResponseBody List getDocument() {
        return arquivoService.findAll();
    }
}

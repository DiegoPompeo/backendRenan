package com.redesocial.ppads.repository;

import com.redesocial.ppads.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
    List<Arquivo> findByArtigoId(Integer artigoId);
}

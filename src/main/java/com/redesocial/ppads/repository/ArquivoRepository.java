package com.redesocial.ppads.repository;

import com.redesocial.ppads.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
    Arquivo findByArtigoId(Integer artigoId);
}

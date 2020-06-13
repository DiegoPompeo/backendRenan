package com.redesocial.ppads.repository;

import com.redesocial.ppads.entity.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Integer> {
    List<Artigo> findByEmailAutor(String emailAutor);
}

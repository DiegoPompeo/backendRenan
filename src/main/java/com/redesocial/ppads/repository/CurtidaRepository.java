package com.redesocial.ppads.repository;

import com.redesocial.ppads.entity.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {
    Curtida findByIdPostCurtidoAndIdUsuarioCurtiu(Integer idPostCurtido, Integer idUsuarioCurtiu);
}

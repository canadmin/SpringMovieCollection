package com.ozguryazilimProje.FilmArsivi.dao;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ozguryazilimProje.FilmArsivi.entities.Filmler;

@Repository
public interface FilmRepoDao extends JpaRepository<Filmler, Integer> {

	public List<Filmler> findByFilmAdContaining(String filmAd);

	public List<Filmler> findByFilmTuruContaining(String filmTuru);
}

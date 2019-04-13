package com.ozguryazilimProje.FilmArsivi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Oyuncular;

@Repository
public interface OyuncuRepoDao extends JpaRepository<Oyuncular, Integer> {
	public List<Oyuncular> findByOyuncuAdContaining(String oyuncuAd);

}

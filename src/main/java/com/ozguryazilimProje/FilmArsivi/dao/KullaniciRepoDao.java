package com.ozguryazilimProje.FilmArsivi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilimProje.FilmArsivi.entities.Kullanicilar;

public interface KullaniciRepoDao extends JpaRepository<Kullanicilar, String> {

}

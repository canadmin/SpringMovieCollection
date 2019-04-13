package com.ozguryazilimProje.FilmArsivi.business;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Oyuncular;
import com.ozguryazilimProje.FilmArsivi.utils.ListeOyuncular;

public interface InterfaceFilmService {
	List<Filmler> filmleriGetir();

	void filmEkle(Filmler film, String oyuncular);

	void filmGuncelle(Filmler film, String oyuncular);

	List<Filmler> filmAra(String ad, String aramaTuru);

	void filmSil(int film);

	Filmler tekFilmGetir(int id);

	List<ListeOyuncular> oyuncuParcala(String oyuncular);

	List<Oyuncular> oyuncular(String ad);

	List<Filmler> filmDesc();

	List<Filmler> filmAsc();

}

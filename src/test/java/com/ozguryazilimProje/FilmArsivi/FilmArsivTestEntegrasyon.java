package com.ozguryazilimProje.FilmArsivi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ozguryazilimProje.FilmArsivi.business.FilmService;
import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Oyuncular;

@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.profiles.active.dev"})
public class FilmArsivTestEntegrasyon {

	@Autowired
	private FilmService filmService;
	

	@Before
	public void filmEkle() {
		Filmler film=new Filmler();
		film.setFilmAd("Tanrı kent");
		film.setFilmAciklama("Brezilya gettolarında geçen bir film");
		film.setFilmTuru("Drama,Ghetto");
		film.setFilmDilSecenekleri("İngizce,Fransızca,Turkce");
		film.setYayinYili(2002);
		film.setFilmId(1);
		String oyuncular="Can Yardımcı:oyuncu1 rol,Barış Can: oyuncu2 rol";
		this.filmService.filmEkle(film, oyuncular);
		
		
		
		
	}
	
	@Test
	public void filmGetir() {
		List<Filmler> film=this.filmService.filmAra("kent", "film");
		assertNotNull(film);
		
	}
	@Test
	public void filmBul() {
		Filmler film=this.filmService.tekFilmGetir(1);
		assertNotNull(film);
		

	}
	
	@Test
	public void oyuncuBul() {
		
		List<Oyuncular> oyuncular=this.filmService.oyuncular("Bar");// oyuncular içinde adı bar icerenleri getir
		assertNotNull(oyuncular);
		

	}
	
	
}

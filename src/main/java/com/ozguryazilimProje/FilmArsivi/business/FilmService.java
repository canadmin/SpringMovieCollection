package com.ozguryazilimProje.FilmArsivi.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.Query;
import com.ozguryazilimProje.FilmArsivi.dao.FilmCrudDao;
import com.ozguryazilimProje.FilmArsivi.dao.FilmRepoDao;
import com.ozguryazilimProje.FilmArsivi.dao.OyuncuRepoDao;
import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Oyuncular;
import com.ozguryazilimProje.FilmArsivi.utils.ListeOyuncular;

@Service
public class FilmService implements InterfaceFilmService {

	@Autowired
	private FilmRepoDao filmRepoDao;

	@Autowired
	private OyuncuRepoDao oyuncuRepoDao;

	@Override
	@Transactional
	public List<Filmler> filmleriGetir() {
		return filmRepoDao.findAll();

	}

	@Override
	@Transactional
	public void filmEkle(Filmler film, String oyuncular) {
		String oyuncuAd = "";
		Filmler duzenlenmisFilm = new Filmler();

		String oyuncuRol = "";
		if (!oyuncular.equals("")) {
			oyuncular = oyuncular.trim();
			String[] tumOyuncular = oyuncular.split(",");
			List<Oyuncular> oyuncuListesi1 = new ArrayList<Oyuncular>();
			Oyuncular[] oyunculistesi = new Oyuncular[tumOyuncular.length];
			for (int i = 0; i <= tumOyuncular.length - 1; i++) {
				Oyuncular olustur = new Oyuncular();
				if (tumOyuncular[i].contains(":")) {
					String[] adVeRol = tumOyuncular[i].split(":");
					oyuncuAd = adVeRol[0];
					oyuncuRol = adVeRol[1];
					olustur.setOyuncuAd(oyuncuAd);
					olustur.setOyuncuRol(oyuncuRol);
					oyunculistesi[i] = olustur;
				} else {
					oyuncuAd = tumOyuncular[i];
					olustur.setOyuncuAd(oyuncuAd);
					olustur.setOyuncuRol("");
					oyunculistesi[i] = olustur;
				}
			}
			duzenlenmisFilm.setFilmOyuncular(Arrays.asList(oyunculistesi));

		}

		duzenlenmisFilm.setFilmAd(film.getFilmAd());
		duzenlenmisFilm.setFilmTuru(film.getFilmTuru());
		duzenlenmisFilm.setFilmAfis(film.getFilmAfis());
		duzenlenmisFilm.setFilmDilSecenekleri(film.getFilmDilSecenekleri());
		duzenlenmisFilm.setFilmAciklama(film.getFilmAciklama());
		duzenlenmisFilm.setYayinYili(film.getYayinYili());
		duzenlenmisFilm.setOyuncular(oyuncular);
		filmRepoDao.save(duzenlenmisFilm);

	}

	@Override
	@Transactional
	public void filmGuncelle(Filmler film, String oyuncular) {
		Filmler silinmeyecek = this.tekFilmGetir(film.getFilmId());
		byte[] image = silinmeyecek.getFilmAfis();
		Filmler duzenlenmisFilm = new Filmler();

		String oyuncuAd = "";
		String oyuncuRol = "";

		if (!oyuncular.equals("")) {
			oyuncular = oyuncular.trim();
			String[] tumOyuncular = oyuncular.split(",");
			List<Oyuncular> oyuncuListesi1 = new ArrayList<Oyuncular>();
			Oyuncular[] oyunculistesi = new Oyuncular[tumOyuncular.length];
			for (int i = 0; i <= tumOyuncular.length - 1; i++) {
				Oyuncular olustur = new Oyuncular();
				if (tumOyuncular[i].contains(":")) {
					String[] adVeRol = tumOyuncular[i].split(":");
					oyuncuAd = adVeRol[0];
					oyuncuRol = adVeRol[1];
					olustur.setOyuncuAd(oyuncuAd);
					olustur.setOyuncuRol(oyuncuRol);
					oyunculistesi[i] = olustur;
				} else {
					oyuncuAd = tumOyuncular[i];
					olustur.setOyuncuAd(oyuncuAd);
					olustur.setOyuncuRol("");
					oyunculistesi[i] = olustur;
				}
			}
			if (film.getFilmOyuncular() != null) {
				for (int i = 0; i <= film.getFilmOyuncular().size() - 1; i++) {

					this.oyuncuRepoDao.delete(film.getFilmOyuncular().get(i));
				}
			}
			duzenlenmisFilm.setFilmOyuncular(Arrays.asList(oyunculistesi));

		} else {
			if (film.getFilmOyuncular() != null) {
				for (int i = 0; i <= film.getFilmOyuncular().size() - 1; i++) {

					this.oyuncuRepoDao.delete(film.getFilmOyuncular().get(i));
				}
			}
		}
		duzenlenmisFilm.setFilmId(film.getFilmId());
		duzenlenmisFilm.setFilmAd(film.getFilmAd());
		duzenlenmisFilm.setFilmTuru(film.getFilmTuru());
		duzenlenmisFilm.setFilmAfis(image);
		duzenlenmisFilm.setFilmDilSecenekleri(film.getFilmDilSecenekleri());
		duzenlenmisFilm.setFilmAciklama(film.getFilmAciklama());
		duzenlenmisFilm.setYayinYili(film.getYayinYili());
		duzenlenmisFilm.setOyuncular(oyuncular);
		filmRepoDao.save(duzenlenmisFilm);

	}

	@Override
	@Transactional
	public void filmSil(int film) {
		filmRepoDao.deleteById(film);

	}

	@Override
	@Transactional
	public Filmler tekFilmGetir(int id) {
		return filmRepoDao.getOne(id);
	}

	@Override
	public List<ListeOyuncular> oyuncuParcala(String oyuncular) {
		List<ListeOyuncular> oyuncu = new ArrayList<ListeOyuncular>();

		String[] adVeRol = oyuncular.split(",");
		String adrol;
		for (int i = 0; i < adVeRol.length; i++) {
			ListeOyuncular listeOyuncular = new ListeOyuncular();
			adrol = adVeRol[i];
			listeOyuncular.setOyuncuAdi(adrol);
			oyuncu.add(listeOyuncular);
		}

		return oyuncu;
	}

	@Override
	public List<Oyuncular> oyuncular(String ad) {
		List<Oyuncular> oyuncular = this.oyuncuRepoDao.findByOyuncuAdContaining(ad);
		return oyuncular;
	}

	@Override
	@Transactional
	public List<Filmler> filmAra(String ad, String aramaTuru) {
		if (aramaTuru.equals("film")) {
			List<Filmler> filmler = this.filmRepoDao.findByFilmAdContaining(ad);
			return filmler;
		} else {
			List<Filmler> filmler = this.filmRepoDao.findByFilmTuruContaining(ad);
			return filmler;
		}
	}

	@Override
	public List<Filmler> filmDesc() {
		List<Filmler> filmler = this.filmRepoDao.findAll(azalanaGore());
		return filmler;
	}

	public Sort azalanaGore() {
		return new Sort(Sort.Direction.DESC, "yayinYili");
	}

	@Override
	public List<Filmler> filmAsc() {

		List<Filmler> filmler = this.filmRepoDao.findAll(artanaGore());
		return filmler;
	}

	public Sort artanaGore() {
		return new Sort(Sort.Direction.ASC, "yayinYili");
	}

}

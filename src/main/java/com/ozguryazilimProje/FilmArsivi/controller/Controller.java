package com.ozguryazilimProje.FilmArsivi.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ozguryazilimProje.FilmArsivi.business.InterfaceFilmService;
import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Medya;
import com.ozguryazilimProje.FilmArsivi.entities.Oyuncular;
import com.ozguryazilimProje.FilmArsivi.utils.ListeOyuncular;

@org.springframework.stereotype.Controller
public class Controller {

	private InterfaceFilmService filmService;

	@Autowired
	public Controller(InterfaceFilmService filmService) {
		this.filmService = filmService;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAnaSayfa() {

		return "index";
	}

	@RequestMapping(value = "/filmler", method = RequestMethod.GET)
	public String getView(Model model, @RequestParam(defaultValue = "") String ara,
			@RequestParam(defaultValue = "film") String tur) {
		List<Filmler> filmler = filmService.filmAra(ara, tur);
		model.addAttribute("filmler", filmler);
		return "icerik/filmler";
	}

	@RequestMapping(value = "/azalan", method = RequestMethod.GET)
	public String azalan(Model model) {
		List<Filmler> filmler = this.filmService.filmDesc();
		model.addAttribute("filmler", filmler);
		return "icerik/filmler";

	}

	@RequestMapping(value = "/artan", method = RequestMethod.GET)
	public String artan(Model model) {
		List<Filmler> filmler = this.filmService.filmAsc();
		model.addAttribute("filmler", filmler);
		return "icerik/filmler";

	}

	@RequestMapping(value = "/addfilm", method = RequestMethod.GET)
	public String ekleme(Model model) {
		model.addAttribute("filmler", new Filmler());
		return "icerik/ekle";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFilm(HttpServletRequest httpServletRequest, @ModelAttribute("Filmler") Filmler viewElemanlarFilm,
			BindingResult result, @RequestParam("images") MultipartFile file) throws IOException {
		Filmler filmler = new Filmler();
		filmler.setFilmAd(viewElemanlarFilm.getFilmAd());
		filmler.setYayinYili(viewElemanlarFilm.getYayinYili());
		filmler.setFilmTuru(viewElemanlarFilm.getFilmTuru());
		filmler.setFilmAciklama(viewElemanlarFilm.getFilmAciklama());
		filmler.setFilmAfis(file.getBytes());
		filmler.setFilmDilSecenekleri(viewElemanlarFilm.getFilmDilSecenekleri());
		String oyuncular = viewElemanlarFilm.getOyuncular();
		this.filmService.filmEkle(filmler, oyuncular);
		return "redirect:/filmler";
	}

	@RequestMapping(value = "/filmGetir", method = RequestMethod.GET)
	public String TekfilmGetir(String id, Model model, HttpSession session)
			throws SerialException, SQLException, IOException {
		int id1 = Integer.parseInt(id);
		Filmler film = filmService.tekFilmGetir(id1);

		if (film.getOyuncular() != null) {
			List<ListeOyuncular> oyuncu = this.filmService.oyuncuParcala(film.getOyuncular());

			model.addAttribute("oyuncu", oyuncu);
		}
		byte[] image = film.getFilmAfis();

		model.addAttribute("image", Base64.getEncoder().encodeToString(film.getFilmAfis()));
		model.addAttribute("film", film);
		return "icerik/filmGetir";
	}

	@RequestMapping(value = "/filmSil/{filmId}", method = RequestMethod.GET)
	public String filmSil(Model model, @PathVariable("filmId") Integer filmId) {
		this.filmService.filmSil(filmId);
		return "redirect:/filmler";

	}

	@RequestMapping(value = "/filmguncelle", method = RequestMethod.GET)
	public String filmGuncelle(String id, Model model, HttpSession session) {
		int id1 = Integer.parseInt(id);
		Filmler film = filmService.tekFilmGetir(id1);
		System.out.println(film);
		model.addAttribute("film", film);
		model.addAttribute("guncelle", film);
		return "icerik/guncelle";
	}

	@RequestMapping(value = "/filmguncelle/{filmId}", method = RequestMethod.POST)
	public String guncelle(@PathVariable("filmId") Integer filmId, Model model, HttpSession session,
			HttpServletRequest httpServletRequest, @ModelAttribute("guncelle") Filmler viewElemanlarFilm) {
		System.out.println(viewElemanlarFilm.getFilmAd());
		Filmler filmler = new Filmler();
		System.out.println(viewElemanlarFilm.getFilmId());
		filmler.setFilmId(filmId);
		filmler.setFilmAd(viewElemanlarFilm.getFilmAd());
		filmler.setYayinYili(viewElemanlarFilm.getYayinYili());
		filmler.setFilmTuru(viewElemanlarFilm.getFilmTuru());
		filmler.setOyuncular(viewElemanlarFilm.getOyuncular());
		filmler.setFilmAciklama(viewElemanlarFilm.getFilmAciklama());
		filmler.setFilmOyuncular(viewElemanlarFilm.getFilmOyuncular());
		filmler.setFilmDilSecenekleri(viewElemanlarFilm.getFilmDilSecenekleri());
		this.filmService.filmGuncelle(filmler, viewElemanlarFilm.getOyuncular());
		return "redirect:/filmler";
	}

	@RequestMapping(value = "/oyuncular", method = RequestMethod.GET)
	public String oyunculariListele(Model model, @RequestParam(defaultValue = "") String arama) {
		List<Oyuncular> oyuncular = this.filmService.oyuncular(arama);

		model.addAttribute("oyuncular", oyuncular);
		return "icerik/oyuncular";
	}

}

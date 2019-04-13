package com.ozguryazilimProje.FilmArsivi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ozguryazilimProje.FilmArsivi.business.InterfaceKullaniciService;
import com.ozguryazilimProje.FilmArsivi.entities.Filmler;
import com.ozguryazilimProje.FilmArsivi.entities.Kullanicilar;

@Controller
public class GirisController {

	private InterfaceKullaniciService interfaceKullaniciService;

	@Autowired
	public GirisController(InterfaceKullaniciService interfaceKullaniciService) {
		this.interfaceKullaniciService = interfaceKullaniciService;
	}

	@RequestMapping(value = "/kayit", method = RequestMethod.GET)
	public String kayit(Model model) {
		model.addAttribute("kullanici", new Kullanicilar());
		return "icerik/kayit";
	}

	@RequestMapping(value = "/kayit", method = RequestMethod.POST)
	public String kayitOl(@Valid Kullanicilar kullanici, BindingResult bindingResult, Model model,
			@RequestParam String tip) {
		if (bindingResult.hasErrors()) {
			return "icerik/kayit";
		}
		System.out.println(tip);
		if (tip.equals("admin")) {
			interfaceKullaniciService.yoneticicOlustur(kullanici);
		} else {
			interfaceKullaniciService.kullaniciOlustur(kullanici);
		}
		return "icerik/giris";

	}

	@GetMapping("/login")
	public String girisYap() {
		return "icerik/giris";
	}

}

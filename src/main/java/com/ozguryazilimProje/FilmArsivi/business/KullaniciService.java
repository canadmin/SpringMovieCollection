package com.ozguryazilimProje.FilmArsivi.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ozguryazilimProje.FilmArsivi.dao.KullaniciRepoDao;
import com.ozguryazilimProje.FilmArsivi.entities.Kullanicilar;
import com.ozguryazilimProje.FilmArsivi.entities.Yetki;

@Service
public class KullaniciService implements InterfaceKullaniciService {

	@Autowired
	private KullaniciRepoDao kullaniciRepoDao;

	@Override
	public void kullaniciOlustur(Kullanicilar kullanici) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		kullanici.setPassword(encoder.encode(kullanici.getPassword()));
		Yetki kullaniciYetki = new Yetki("USER");
		List<Yetki> yetkiler = new ArrayList<>();
		yetkiler.add(kullaniciYetki);
		kullanici.setYetkiler(yetkiler);
		kullaniciRepoDao.save(kullanici);

	}

	@Override
	public void yoneticicOlustur(Kullanicilar kullanici) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		kullanici.setPassword(encoder.encode(kullanici.getPassword()));
		Yetki kullaniciYetki = new Yetki("ADMIN");
		List<Yetki> yetkiler = new ArrayList<>();
		yetkiler.add(kullaniciYetki);
		kullanici.setYetkiler(yetkiler);
		kullaniciRepoDao.save(kullanici);
	}

}

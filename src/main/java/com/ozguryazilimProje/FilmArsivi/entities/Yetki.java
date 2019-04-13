package com.ozguryazilimProje.FilmArsivi.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Yetki")
@Table(name = "yetki")
public class Yetki {

	@Id
	@Column
	private String kulliniciAd;
	@Column
	@ManyToMany(mappedBy = "yetkiler")
	private List<Kullanicilar> kullanicilar;

	public String getKulliniciAd() {
		return kulliniciAd;
	}

	public void setKulliniciAd(String kulliniciAd) {
		this.kulliniciAd = kulliniciAd;
	}

	public List<Kullanicilar> getKullanicilar() {
		return kullanicilar;
	}

	public void setKullanicilar(List<Kullanicilar> kullanicilar) {
		this.kullanicilar = kullanicilar;
	}

	public Yetki(String kulliniciAd) {
		this.kulliniciAd = kulliniciAd;
		this.kullanicilar = kullanicilar;
	}

	public Yetki() {
		// TODO Auto-generated constructor stub
	}

}

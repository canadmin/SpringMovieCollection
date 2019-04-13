package com.ozguryazilimProje.FilmArsivi.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Kullanicilar")
@Table(name = "kullanicilar")
@EntityListeners(AuditingEntityListener.class)
public class Kullanicilar {

	@Id
	@Email
	@NotEmpty
	@Column(unique = true)
	private String email;
	@Basic
	@NotEmpty
	@Column(name = "kullaniciAd")
	private String kullaniciAd;
	@Basic
	@NotEmpty
	@Size(min = 6)
	@Column(name = "password")
	private String password;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "kullanici_yetkiler", joinColumns = {
			@JoinColumn(name = "user_email", referencedColumnName = "email") }, inverseJoinColumns = {
					@JoinColumn(name = "yetki_kullaniciad", referencedColumnName = "kulliniciAd") })
	private List<Yetki> yetkiler;

	public Kullanicilar() {

	}

	public Kullanicilar(String email, String kullaniciAd, String password) {
		this.email = email;
		this.kullaniciAd = kullaniciAd;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKullaniciAd() {
		return kullaniciAd;
	}

	public void setKullaniciAd(String kullaniciAd) {
		this.kullaniciAd = kullaniciAd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Yetki> getYetkiler() {
		return yetkiler;
	}

	public void setYetkiler(List<Yetki> yetkiler) {
		this.yetkiler = yetkiler;
	}

}

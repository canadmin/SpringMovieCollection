package com.ozguryazilimProje.FilmArsivi.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

@Entity(name = "Filmler")
@Table(name = "filmler")
@EntityListeners(AuditingEntityListener.class)
public class Filmler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4868624973343083042L;

	@Basic
	@Id
	@SequenceGenerator(name = "FILMLER_SEQUENCE_GENERATOR", sequenceName = "FILMLER_SEQUENCE", initialValue = 1)
	@Column(name = "filmId")
	@GeneratedValue(generator = "FILMLER_SEQUENCE_GENERATOR")
	private int filmId;

	@Basic
	@Column(name = "filmAd")
	private String filmAd;

	@Basic
	@Column(name = "filmAciklama", length = 500)
	private String filmAciklama;

	@Lob
	@Column(name = "filmAfis")
	private byte[] filmAfis;
	@Basic
	@Column(name = "filmDilSecenekleri")
	private String filmDilSecenekleri;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@Column(unique = true)
	private List<Oyuncular> filmOyuncular;
	@Basic
	@Column(name = "yayinYili")
	private int yayinYili;

	@Basic
	@Column(name = "filmTuru")
	private String filmTuru;

	public Filmler() {
		// TODO Auto-generated constructor stub
	}

	public List<Oyuncular> getFilmOyuncular() {
		return filmOyuncular;
	}

	public String getOyuncular() {
		return oyuncular;
	}

	public void setOyuncular(String oyuncular) {
		this.oyuncular = oyuncular;
	}

	public void setFilmOyuncular(List<Oyuncular> filmOyuncular) {
		this.filmOyuncular = filmOyuncular;
	}

	public Filmler(int filmId, String filmAd, String filmAciklama, byte[] filmAfis, String filmDilSecenekleri,
			int yayinYili, String filmTuru) {
		this.filmId = filmId;
		this.filmAd = filmAd;
		this.filmAciklama = filmAciklama;
		this.filmAfis = filmAfis;
		this.filmDilSecenekleri = filmDilSecenekleri;
		this.yayinYili = yayinYili;
		this.filmTuru = filmTuru;
	}

	public String getFilmDilSecenekleri() {
		return filmDilSecenekleri;
	}

	public void setFilmDilSecenekleri(String filmDilSecenekleri) {
		this.filmDilSecenekleri = filmDilSecenekleri;
	}

	public byte[] getFilmAfis() {
		return filmAfis;
	}

	public void setFilmAfis(byte[] filmAfis) {
		this.filmAfis = filmAfis;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getFilmTuru() {
		return filmTuru;
	}

	public void setFilmTuru(String filmTuru) {
		this.filmTuru = filmTuru;
	}

	public String getFilmAd() {
		return filmAd;
	}

	public void setFilmAd(String filmAd) {
		this.filmAd = filmAd;
	}

	public String getFilmAciklama() {
		return filmAciklama;
	}

	public void setFilmAciklama(String filmAciklama) {
		this.filmAciklama = filmAciklama;
	}

	public int getYayinYili() {
		return yayinYili;
	}

	public void setYayinYili(int yayinYili) {
		this.yayinYili = yayinYili;
	}

	private String oyuncular;

	public Filmler(int filmId, String filmAd, String filmAciklama, byte[] filmAfis, String filmDilSecenekleri,
			int yayinYili, String filmTuru, String oyuncular) {

		this.filmId = filmId;
		this.filmAd = filmAd;
		this.filmAciklama = filmAciklama;
		this.filmAfis = filmAfis;
		this.filmDilSecenekleri = filmDilSecenekleri;
		this.yayinYili = yayinYili;
		this.filmTuru = filmTuru;
		this.oyuncular = oyuncular;
	}

}

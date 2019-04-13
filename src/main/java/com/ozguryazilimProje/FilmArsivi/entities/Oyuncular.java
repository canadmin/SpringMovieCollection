package com.ozguryazilimProje.FilmArsivi.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Oyuncular")
@Table(name = "oyuncular")
@EntityListeners(AuditingEntityListener.class)
public class Oyuncular {

	@Id
	@SequenceGenerator(name = "OYUNCULAR_SEQUENCE_GENERATOR", sequenceName = "OYUNCULAR_SEQUENCE", initialValue = 100)
	@Column(name = "oyuncuId")
	@GeneratedValue(generator = "OYUNCULAR_SEQUENCE_GENERATOR")
	private int oyuncuId;

	@Basic
	@Column(name = "oyuncuAd")
	private String oyuncuAd;
	@Basic
	@Column(name = "oyuncuRol")
	private String oyuncuRol;

	@Basic
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filmid")
	// @GeneratedValue(generator="OYUNCULAR_SEQUENCE_GENERATOR")
	private Filmler filmId;

	public Oyuncular(int oyuncuId, String oyuncuAd, String oyuncuRol) {
		/* this.oyuncuId = oyuncuId; */
		this.oyuncuAd = oyuncuAd;
		this.oyuncuRol = oyuncuRol;
	}

	public Filmler getFilmId() {
		return filmId;
	}

	public void setFilmId(Filmler filmId) {
		this.filmId = filmId;
	}

	public Oyuncular() {
		// TODO Auto-generated constructor stub
	}

	public int getOyuncuId() {
		return oyuncuId;
	}

	public void setOyuncuId(int oyuncuId) {
		this.oyuncuId = oyuncuId;
	}

	public String getOyuncuAd() {
		return oyuncuAd;
	}

	public void setOyuncuAd(String oyuncuAd) {
		this.oyuncuAd = oyuncuAd;
	}

	public String getOyuncuRol() {
		return oyuncuRol;
	}

	public void setOyuncuRol(String oyuncuRol) {
		this.oyuncuRol = oyuncuRol;

	}

}

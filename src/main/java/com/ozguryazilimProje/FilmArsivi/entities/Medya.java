package com.ozguryazilimProje.FilmArsivi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import groovy.transform.Generated;

//@Entity
//Table(name="film_medya")
public class Medya implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808963093852368455L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "uzanti")
	private String uzanti;
	@OneToOne
	@JoinColumn(name = "film_id")
	private Filmler film;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUzanti() {
		return uzanti;
	}

	public void setUzanti(String uzanti) {
		this.uzanti = uzanti;
	}

	public Filmler getFilm() {
		return film;
	}

	public void setFilm(Filmler film) {
		this.film = film;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

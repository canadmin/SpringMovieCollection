package com.ozguryazilimProje.FilmArsivi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ozguryazilimProje.FilmArsivi.entities.Filmler;

@Qualifier("filmRepo")
@Transactional
public interface FilmCrudDao extends CrudRepository<Filmler, Integer> {

}

package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId) throws SQLException;

	List<Film> findFilmsByActorId(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> findFilmByKeyWord(String keyWord);

	public String findLanguageByFilmId(int FilmId);

	public List<Actor> seeCastFilmId(int castFilmId);
}

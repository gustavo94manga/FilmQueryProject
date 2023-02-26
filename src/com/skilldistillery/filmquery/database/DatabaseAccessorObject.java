package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String sql = "SELECT id, title, description, release_year,"
				+ "language_id, rental_duration, rental_rate, length," + "replacement_cost, rating, special_features"
				+ " FROM film WHERE film.id = ?";
		ResultSet filmResult = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			filmResult = stmt.executeQuery();
			if (filmResult.next()) {

				film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"));
				film.setLanguageName(findLanguageByFilmId(filmId));
				film.setActors(seeCastFilmId(filmId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				filmResult.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return film;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, actorId);

			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {

				Film film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"));
				film.setLanguageName(findLanguageByFilmId(film.getId()));
				film.setActors(seeCastFilmId(film.getId()));
				films.add(film);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pass);

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {

			actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
					actorResult.getString("last_name"));
			actor.setFilms(findFilmsByActorId(actorId));

		}

		actorResult.close();
		stmt.close();
		conn.close();

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.*" + " FROM actor" + " JOIN film_actor ON actor.id = film_actor.actor_id"
					+ " JOIN film ON film_actor.film_id = film.id" + " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyWord(String keyWord) {
		List<Film> searchByKeyWord = new ArrayList<>();

		String sql = "SELECT id, title, description, release_year,"
				+ " language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features"
				+ " FROM film WHERE film.title LIKE ? OR film.description LIKE ?";
		ResultSet filmResult = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			filmResult = stmt.executeQuery();
			while (filmResult.next()) {

				Film film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"));
				film.setLanguageName(findLanguageByFilmId(film.getId()));
				film.setActors(seeCastFilmId(film.getId()));
				searchByKeyWord.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				filmResult.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return searchByKeyWord;
	}

	@Override
	public String findLanguageByFilmId(int FilmId) {
//		Film film = null;

		String sql = "SELECT film.id, film.title, film.description, l.name" + " FROM language l"
				+ " JOIN film ON l.id = film.id" + " WHERE film.id = ?";
		ResultSet filmResult = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, FilmId);
			filmResult = stmt.executeQuery();
			if (filmResult.next()) {

				return filmResult.getString("l.name");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				filmResult.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return "English";
	}

	@Override
	public List<Actor> seeCastFilmId(int castFilmId) {
		List<Actor> actors = new ArrayList<>();

		String sql = "SELECT actor.*, film.id, film.title, film.description, film.release_year, film.rating"
				+ " FROM actor" + " JOIN film_actor ON actor.id = film_actor.actor_id"
				+ " JOIN film ON film_actor.film_id = film.id" + " WHERE film.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, castFilmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {

				Actor actor = new Actor(actorResult.getInt("actor.id"), actorResult.getString("actor.first_name"),
						actorResult.getString("actor.last_name"));
				actors.add(actor);
			}

			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return actors;
	}

}

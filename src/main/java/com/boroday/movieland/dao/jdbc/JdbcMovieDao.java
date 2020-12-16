package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.MovieDao;
import com.boroday.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.boroday.movieland.entity.Movie;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private static final String GET_ALL_MOVIES = "select id, nameRu, nameEn, yearOfProduction, description, rating, price from movies";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private DataSource dataSource;

    public JdbcMovieDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Movie> getAll() {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MOVIES);
            ResultSet resultSet = preparedStatement.executeQuery()){

            List<Movie> listOfMovies = new ArrayList<>();
            while (resultSet.next()){
                Movie movie = MOVIE_ROW_MAPPER.mapRow(resultSet);
                listOfMovies.add(movie);
            }
            return listOfMovies;
        } catch (SQLException e) {
            throw new RuntimeException("Impossible to get all movies", e);
        }
    }
}

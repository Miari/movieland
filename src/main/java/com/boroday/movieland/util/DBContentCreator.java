package com.boroday.movieland.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
public class DBContentCreator {
    private static final String pathToUsersFile = "src/main/resources/dbContent/users.txt";
    private static final String pathToUsersDbMigrationFile = "src/main/resources/db/migration/V1_1__fill_users.sql";
    private static final String pathToMoviesFile = "src/main/resources/dbContent/movies.txt";
    private static final String pathToMoviesDbMigrationFile = "src/main/resources/db/migration/V1_2__fill_movies.sql";
    private static final String pathToGenresFile = "src/main/resources/dbContent/genres.txt";
    private static final String pathToGenresDbMigrationFile = "src/main/resources/db/migration/V1_3__fill_genres.sql";
    private static final String pathToPostersFile = "src/main/resources/dbContent/posters.txt";
    private static final String pathToPostersDbMigrationFile = "src/main/resources/db/migration/V1_4__fill_posters.sql";
    private static final String pathToCountriesDbMigrationFile = "src/main/resources/db/migration/V1_5__fill_countries.sql";
    private static final String pathToCountryMovieDbMigrationFile = "src/main/resources/db/migration/V1_6__fill_country_movie.sql";
    private static final String pathToGenreMovieDbMigrationFile = "src/main/resources/db/migration/V1_7__fill_genre_movie.sql";
    private static final String pathToReviewsFile = "src/main/resources/dbContent/reviews.txt";
    private static final String pathToReviewsDbMigrationFile = "src/main/resources/db/migration/V1_8__fill_reviews.sql";

    ArrayList<String> fullName = new ArrayList<>();
    ArrayList<String> movieNameRu = new ArrayList<>();

    public void fillUsers() {
        ArrayList<String> firstName = new ArrayList<>();
        ArrayList<String> lastName = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> password = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToUsersFile))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(" ")) {
                    fullName.add(line);
                    int indexOf = line.indexOf(" ");
                    firstName.add(line.substring(0, indexOf));
                    lastName.add(line.substring(indexOf + 1));
                } else if (line.contains("@")) {
                    email.add(line);
                } else if (!line.isEmpty()) {
                    password.add(line);
                }
            }
        } catch (IOException e) {
            log.error("Not possible to read file " + pathToUsersFile);
            throw new RuntimeException("Not possible to read file", e);
        }

        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToUsersDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < firstName.size(); i++) {
                stringBuilder.append("INSERT INTO users (firstname, lastname, email, password) VALUES ('");
                stringBuilder.append(firstName.get(i));
                stringBuilder.append("', '");
                stringBuilder.append(lastName.get(i));
                stringBuilder.append("', '");
                stringBuilder.append(email.get(i));
                stringBuilder.append("', '");
                stringBuilder.append(password.get(i));
                stringBuilder.append("'); \n");
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToUsersDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }
    }

    public void fillMovies() {
        ArrayList<String> movieNameEn = new ArrayList<>();
        ArrayList<Integer> yearOfProduction = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<Double> rating = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> countryMovie = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> genreMovie = new HashMap<>();

        ArrayList<String> nameOfGenre = new ArrayList<>();
        fillGenres(nameOfGenre);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToMoviesFile))) {
            String line;
            int idOfMovie = 0;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (index == 1) {
                        int indexOfDelimiter = line.indexOf("/");
                        movieNameRu.add(line.substring(0, indexOfDelimiter));
                        String lineEn = line.substring(indexOfDelimiter + 1);
                        if (lineEn.contains("'")) {
                            int indexOf = lineEn.indexOf("'");
                            line = lineEn.substring(0, indexOf + 1) + "'" + lineEn.substring(indexOf + 1);
                        } else {
                            line = lineEn;
                        }
                        movieNameEn.add(line);
                        idOfMovie++;
                    } else if (index == 2) {
                        yearOfProduction.add(Integer.valueOf(line));
                    } else if (index == 3) {
                        ArrayList<Integer> idOfCountries = new ArrayList<>();
                        String[] allCountries = line.split(", ");
                        for (String country : allCountries) {
                            if (countries.contains(country)) {
                                idOfCountries.add(countries.indexOf(country) + 1);
                            } else {
                                countries.add(country);
                                idOfCountries.add(countries.indexOf(country) + 1);
                            }
                            countryMovie.put(idOfMovie, idOfCountries);
                        }
                    } else if (index == 4) {
                        ArrayList<Integer> idOfGenres = new ArrayList<>();
                        String[] allGenres = line.split(", ");
                        for (String genre : allGenres) {
                            if (nameOfGenre.contains(genre)) {
                                idOfGenres.add(nameOfGenre.indexOf(genre) + 1);
                                genreMovie.put(idOfMovie, idOfGenres);
                            } else {
                                log.error("Not possible to found genre file " + genre);
                                throw new RuntimeException("Genre is not found. Please add genre to genres file firstly");
                            }
                        }
                    } else if (index == 5) {
                        description.add(line);
                    } else if (index == 6) {
                        int indexOf = line.indexOf(":");
                        rating.add(Double.valueOf(line.substring(indexOf + 1)));
                    } else if (index == 7) {
                        int indexOf = line.indexOf(":");
                        price.add(Double.valueOf(line.substring(indexOf + 1)));
                        index = 0;
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            log.error("Not possible to read file " + pathToMoviesFile);
            throw new RuntimeException("Not possible to read file", e);
        }

        // write movies
        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToMoviesDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < movieNameRu.size(); i++) {
                stringBuilder.append("INSERT INTO movies (nameRu, nameEn, yearOfProduction, description, rating, price) VALUES ('");
                stringBuilder.append(movieNameRu.get(i));
                stringBuilder.append("', '");
                stringBuilder.append(movieNameEn.get(i));
                stringBuilder.append("', ");
                stringBuilder.append(yearOfProduction.get(i));
                stringBuilder.append(", '");
                stringBuilder.append(description.get(i));
                stringBuilder.append("', ");
                stringBuilder.append(rating.get(i));
                stringBuilder.append(", ");
                stringBuilder.append(price.get(i));
                stringBuilder.append("); \n");
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToMoviesDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }

        //read posters
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> link = new ArrayList<>();
        readPosters(name, link);

        //write posters
        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToPostersDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < name.size(); i++) {
                int idOfMovie = movieNameRu.indexOf(name.get(i)) + 1;
                stringBuilder.append("INSERT INTO posters (id_movie, link) VALUES (");
                stringBuilder.append(idOfMovie);
                stringBuilder.append(", '");
                stringBuilder.append(link.get(i));
                stringBuilder.append("'); \n");
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToPostersDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }

        // write countries
        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToCountriesDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < countries.size(); i++) {
                stringBuilder.append("INSERT INTO countries (name) VALUES ('");
                stringBuilder.append(countries.get(i));
                stringBuilder.append("'); \n");
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToCountriesDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }

        //write country_movie
        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToCountryMovieDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            Set<Integer> moviesId = countryMovie.keySet();
            for (Integer movieId : moviesId) {
                ArrayList<Integer> countriesId = countryMovie.get(movieId);
                for (Integer countryId : countriesId) {
                    stringBuilder.append("INSERT INTO country_movie (id_movie, id_country) VALUES (");
                    stringBuilder.append(movieId);
                    stringBuilder.append(", ");
                    stringBuilder.append(countryId);
                    stringBuilder.append("); \n");
                }
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToCountryMovieDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }

        //write genre_movie
        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToGenreMovieDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            Set<Integer> moviesId = genreMovie.keySet();
            for (Integer movieId : moviesId) {
                ArrayList<Integer> genresId = genreMovie.get(movieId);
                for (Integer genreId : genresId) {
                    stringBuilder.append("INSERT INTO genre_movie (id_movie, id_genre) VALUES (");
                    stringBuilder.append(movieId);
                    stringBuilder.append(", ");
                    stringBuilder.append(genreId);
                    stringBuilder.append("); \n");
                }
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToGenreMovieDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }
    }

    public void fillReviews() {
        ArrayList<String> reviewMovie = new ArrayList<>();
        ArrayList<String> reviewUserFullName = new ArrayList<>();
        ArrayList<String> reviewUserFirstName = new ArrayList<>();
        ArrayList<String> reviewUserLastName = new ArrayList<>();
        ArrayList<String> reviewDescription = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReviewsFile))) {
            String line;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (index == 1) {
                        reviewMovie.add(line);
                    } else if (index == 2) {
                        reviewUserFullName.add(line);
                        int indexOfDelimiter = line.indexOf(" ");
                        reviewUserFirstName.add(line.substring(0, indexOfDelimiter));
                        reviewUserLastName.add(line.substring(indexOfDelimiter + 1));
                    } else if (index == 3) {
                        reviewDescription.add(line);
                        index = 0;
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            log.error("Not possible to read file " + pathToReviewsFile);
            throw new RuntimeException("Not possible to read file", e);
        }

        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToReviewsDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < reviewMovie.size(); i++) {
                if (movieNameRu.contains(reviewMovie.get(i))) {
                    stringBuilder.append("INSERT INTO reviews (id_movie, id_user, description) VALUES (");
                    stringBuilder.append(movieNameRu.indexOf(reviewMovie.get(i)) + 1);
                    for (String userFullName: reviewUserFullName){
                        if (fullName.contains(userFullName)){
                            stringBuilder.append(", ");
                            stringBuilder.append(fullName.indexOf(reviewUserFullName.get(i)) + 1);
                            break;
                        }
                    }
                    stringBuilder.append(", '");
                    stringBuilder.append(reviewDescription.get(i));
                    stringBuilder.append("'); \n");
                }
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToReviewsDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }

    }

    private void fillGenres(ArrayList<String> nameOfGenre) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToGenresFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    nameOfGenre.add(line);
                }
            }
        } catch (IOException e) {
            log.error("Not possible to read file " + pathToGenresFile);
            throw new RuntimeException("Not possible to read file", e);
        }

        try (BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(pathToGenresDbMigrationFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < nameOfGenre.size(); i++) {
                stringBuilder.append("INSERT INTO genres (name) VALUES ('");
                stringBuilder.append(nameOfGenre.get(i));
                stringBuilder.append("'); \n");
            }
            stringBuilder.append("COMMIT;");
            bufferedWritter.write(stringBuilder.toString());
        } catch (IOException e) {
            log.error("Not possible to write into file " + pathToGenresDbMigrationFile);
            throw new RuntimeException("Not possible to write file", e);
        }
    }

    private void readPosters(ArrayList<String> name, ArrayList<String> link) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToPostersFile))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("http")) {
                    int indexOf = line.indexOf("http");
                    name.add(line.substring(0, indexOf - 1));
                    link.add(line.substring(indexOf));
                }
            }
        } catch (IOException e) {
            log.error("Not possible to read file " + pathToPostersFile);
            throw new RuntimeException("Not possible to read file", e);
        }
    }
}

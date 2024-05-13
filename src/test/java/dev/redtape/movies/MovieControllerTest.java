package dev.redtape.movies;

import dev.redtape.movies.movie.MovieController;
import dev.redtape.movies.movie.MovieGetAllDTO;
import dev.redtape.movies.movie.MovieGetByImdbIdDTO;
import dev.redtape.movies.movie.MovieService;
import dev.redtape.movies.review.Review;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    private MovieGetAllDTO movieGetAllDTO;
    private MovieGetByImdbIdDTO movieGetByImdbIdDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ObjectId id = new ObjectId();
        String imdbId = "imdbId";
        String title = "title";
        String releaseDate = "releaseDate";
        String trailerLink = "trailerLink";
        String poster = "poster";
        List<String> genres = Collections.singletonList("genre");
        List<String> backdrops = Collections.singletonList("backdrop");
        List<Review> reviewIds = Collections.singletonList(new Review("Not Bad"));

        movieGetAllDTO = new MovieGetAllDTO(id, imdbId, title, releaseDate, trailerLink, poster, genres, backdrops, reviewIds);
        movieGetByImdbIdDTO = new MovieGetByImdbIdDTO(id, imdbId, title, releaseDate, trailerLink, poster, genres, backdrops, reviewIds);
    }

    @Test
    @DisplayName("getAllMovies returns movies when movies exist")
    public void getAllMoviesReturnsMoviesWhenMoviesExist() {
        when(movieService.allMovies()).thenReturn(Collections.singletonList(movieGetAllDTO));
        ResponseEntity<List<MovieGetAllDTO>> response = movieController.getAllMovies();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("getSingleMovie returns movie when movie exists")
    public void getSingleMovieReturnsMovieWhenMovieExists() {
        when(movieService.singleMovie("imdbId")).thenReturn(Optional.of(movieGetByImdbIdDTO));
        ResponseEntity<MovieGetByImdbIdDTO> response = movieController.getSingleMovie("imdbId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("getSingleMovie returns 404 when movie does not exist")
    public void getSingleMovieReturns404WhenMovieDoesNotExist() {
        when(movieService.singleMovie("imdbId")).thenReturn(Optional.empty());
        ResponseEntity<MovieGetByImdbIdDTO> response = movieController.getSingleMovie("imdbId");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
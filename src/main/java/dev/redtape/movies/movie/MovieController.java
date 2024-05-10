package dev.redtape.movies.movie;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")

public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieGetAllDTO>> getAllMovies() {
        return new ResponseEntity<>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<MovieGetByImdbIdDTO> getSingleMovie(@PathVariable String imdbId){
        Optional<MovieGetByImdbIdDTO> movie = movieService.singleMovie(imdbId);
        return movie.map(movieGetByImdbIdDTO -> new ResponseEntity<>(movieGetByImdbIdDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}

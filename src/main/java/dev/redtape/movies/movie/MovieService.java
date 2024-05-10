package dev.redtape.movies.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieGetAllDTO> allMovies() {
        return movieRepository.findAll().stream()
                .map(movie -> new MovieGetAllDTO(
                        movie.getId(),
                        movie.getImdbId(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getTrailerLink(),
                        movie.getPoster(),
                        movie.getGenres(),
                        movie.getBackdrops(),
                        movie.getReviewIds()))
                .toList();
    }
    public Optional<MovieGetByImdbIdDTO> singleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId)
                .map(movie -> new MovieGetByImdbIdDTO(
                        movie.getId(),
                        movie.getImdbId(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getTrailerLink(),
                        movie.getPoster(),
                        movie.getGenres(),
                        movie.getBackdrops(),
                        movie.getReviewIds()));
    }
}
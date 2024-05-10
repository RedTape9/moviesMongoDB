package dev.redtape.movies.movie;

import dev.redtape.movies.review.Review;
import org.bson.types.ObjectId;

import java.util.List;

public record MovieGetByImdbIdDTO(ObjectId id, String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops, List<Review> reviewIds) {
}
// TODO: clean up later. remove unnecessary fields
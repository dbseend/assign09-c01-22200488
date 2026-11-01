package com.webservice.assign09c0122200488.service;

import com.webservice.assign09c0122200488.domain.Movie;
import com.webservice.assign09c0122200488.dto.MovieRequest;
import com.webservice.assign09c0122200488.dto.MovieResponse;
import com.webservice.assign09c0122200488.exception.MovieNotFoundException;
import com.webservice.assign09c0122200488.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public MovieResponse addMovie(MovieRequest movieRequest){
        validate(movieRequest);

        Movie newMovie = movieRepository.save(new Movie(movieRequest));

        return MovieResponse.from(newMovie);
    }

    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll().stream().map(MovieResponse::from).toList();
    }

    public MovieResponse getMovieById(Long id){
        return movieRepository.findById(id)
                .map(MovieResponse::from)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public MovieResponse updateMovieById(Long id, MovieRequest movieRequest){
        Movie targetMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        validate(movieRequest);

        targetMovie.update(movieRequest);

        return MovieResponse.from(movieRepository.save(targetMovie));
    }

    public void deleteMovieById(Long id){
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException(id);
        }

        movieRepository.deleteById(id);
    }

    private void validate(MovieRequest movieRequest){
        if(movieRequest.title() == null || movieRequest.title().isBlank()){
            throw new IllegalArgumentException("제목은 반드시 입력해야 합니다.");
        }
        if(movieRequest.director() == null || movieRequest.director().isBlank()){
            throw new IllegalArgumentException("감독은 반드시 입력해야 합니다.");
        }
        if(movieRequest.category() == null || movieRequest.category().isBlank()){
            throw new IllegalArgumentException("카테고리는 반드시 입력해야 합니다.");
        }
        if(movieRequest.releaseYear() == null || movieRequest.releaseYear() < 1900 || movieRequest.releaseYear() > 2100){
            throw new IllegalArgumentException("개봉연도는 1900~2100 사이여야 합니다.");
        }
        if(movieRequest.rating() == null || movieRequest.rating() < 0 || movieRequest.rating() > 10){
            throw new IllegalArgumentException("평점은 0~10 사이여야 합니다.");
        }
    }
}

package com.webservice.assign09c0122200488.service;

import com.webservice.assign09c0122200488.domain.Movie;
import com.webservice.assign09c0122200488.dto.MovieRequest;
import com.webservice.assign09c0122200488.dto.MovieResponse;
import com.webservice.assign09c0122200488.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public MovieResponse addBook(MovieRequest bookRequest){
        validate(bookRequest);

        Movie newBook = movieRepository.save(new Movie(bookRequest));

        return MovieResponse.from(newBook);
    }

    public List<MovieResponse> getAllBooks(){
        return movieRepository.findAll().stream().map(MovieResponse::from).toList();
    }

    public Optional<MovieResponse> getBookById(Long id){
        return movieRepository.findById(id).map(MovieResponse::from);
    }

    public MovieResponse updateBookById(Long id, MovieRequest bookRequest){
        Optional<Movie> targetBook = movieRepository.findById(id);
        if(targetBook.isEmpty()){
            throw new IllegalArgumentException("ID에 해당하는 책이 없습니다");
        }

        validate(bookRequest);

        targetBook.get().update(bookRequest);

        return MovieResponse.from(movieRepository.save(targetBook.get()));
    }

    public void deleteBookById(Long id){
        if(!movieRepository.existsById(id)){
            throw new IllegalArgumentException("ID에 해당하는 책이 없습니다");
        }

        movieRepository.deleteById(id);
    }

    private void validate(MovieRequest bookRequest){
        if(bookRequest.title() == null || bookRequest.title().trim().isBlank()){
            throw new IllegalArgumentException("재목은 반드시 입력해야 합니다.");
        }
        if(bookRequest.director() == null || bookRequest.director().trim().isBlank()){
            throw new IllegalArgumentException("작가는 반드시 입력해야 합니다.");
        }
        if(bookRequest.category() == null || bookRequest.category().trim().isBlank()){
            throw new IllegalArgumentException("카테고리는 반드시 입력해야 합니다.");
        }
        if(bookRequest.releaseYear() == null || (bookRequest.releaseYear() < 1900 || bookRequest.releaseYear() > 2100)){
            throw new IllegalArgumentException("가격은 반드시 입력해야 합니다. + 0보다 작을 수 없습니다");
        }
        if(bookRequest.rating() == null || bookRequest.rating() < 0){
            throw new IllegalArgumentException("평점은 반드시 입력해야 합니다. + 0보다 작을 수 없습니다");
        }
    }
}

package com.webservice.assign09c0122200488.controller;

import com.webservice.assign09c0122200488.service.MovieService;
import com.webservice.assign09c0122200488.dto.MovieRequest;
import com.webservice.assign09c0122200488.dto.MovieResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("")
    public ResponseEntity<MovieResponse> createBook(@RequestBody MovieRequest bookRequest){
        MovieResponse bookResponse = movieService.addBook(bookRequest);

        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.id())).body(bookResponse);
    }

    @GetMapping("")
    public List<MovieResponse> getAllBooks(){
        return movieService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<MovieResponse> getBookById(@PathVariable Long id){
        return movieService.getBookById(id);
    }

    @PutMapping("/{id}")
    public MovieResponse updateBookById(@PathVariable Long id, @RequestBody MovieRequest bookRequest){
        return movieService.updateBookById(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

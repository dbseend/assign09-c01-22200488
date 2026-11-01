package com.webservice.assign09c0122200488.controller;

import com.webservice.assign09c0122200488.BookService;
import com.webservice.assign09c0122200488.dto.BookRequest;
import com.webservice.assign09c0122200488.dto.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moives")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest){
        BookResponse bookResponse = bookService.addBook(bookRequest);

        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.id())).body(bookResponse);
    }

    @GetMapping("")
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<BookResponse> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponse updateBookById(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        return bookService.updateBookById(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

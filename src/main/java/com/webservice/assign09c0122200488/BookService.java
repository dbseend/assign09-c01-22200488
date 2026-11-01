package com.webservice.assign09c0122200488;

import com.webservice.assign09c0122200488.domain.Book;
import com.webservice.assign09c0122200488.dto.BookRequest;
import com.webservice.assign09c0122200488.dto.BookResponse;
import com.webservice.assign09c0122200488.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookResponse addBook(BookRequest bookRequest){
        validate(bookRequest);

        Book newBook = bookRepository.save(new Book(bookRequest));

        return BookResponse.from(newBook);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepository.findAll().stream().map(BookResponse::from).toList();
    }

    public Optional<BookResponse> getBookById(Long id){
        return bookRepository.findById(id).map(BookResponse::from);
    }

    public BookResponse updateBookById(Long id, BookRequest bookRequest){
        Optional<Book> targetBook = bookRepository.findById(id);
        if(targetBook.isEmpty()){
            throw new IllegalArgumentException("ID에 해당하는 책이 없습니다");
        }

        validate(bookRequest);

        targetBook.get().update(bookRequest);

        return BookResponse.from(bookRepository.save(targetBook.get()));
    }

    public void deleteBookById(Long id){
        if(!bookRepository.existsById(id)){
            throw new IllegalArgumentException("ID에 해당하는 책이 없습니다");
        }

        bookRepository.deleteById(id);
    }

    private void validate(BookRequest bookRequest){
        if(bookRequest.title() == null || bookRequest.title().trim().isBlank()){
            throw new IllegalArgumentException("재목은 반드시 입력해야 합니다.");
        }
        if(bookRequest.author() == null || bookRequest.author().trim().isBlank()){
            throw new IllegalArgumentException("작가는 반드시 입력해야 합니다.");
        }
        if(bookRequest.category() == null || bookRequest.category().trim().isBlank()){
            throw new IllegalArgumentException("카테고리는 반드시 입력해야 합니다.");
        }
        if(bookRequest.price() == null || bookRequest.price() < 0){
            throw new IllegalArgumentException("가격은 반드시 입력해야 합니다. + 0보다 작을 수 없습니다");
        }
        if(bookRequest.rating() == null || bookRequest.rating() < 0){
            throw new IllegalArgumentException("평점은 반드시 입력해야 합니다. + 0보다 작을 수 없습니다");
        }
    }
}

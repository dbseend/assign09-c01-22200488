package com.webservice.assign09c0122200488.domain;

import com.webservice.assign09c0122200488.dto.BookRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Double rating;

    public Book(BookRequest bookRequest){
        this.title = bookRequest.title();
        this.author = bookRequest.author();
        this.price = bookRequest.price();
        this.category = bookRequest.category();
        this.rating = bookRequest.rating();
    }

    public void update(BookRequest bookRequest){
        this.title = bookRequest.title();
        this.author = bookRequest.author();
        this.price = bookRequest.price();
        this.category = bookRequest.category();
        this.rating = bookRequest.rating();
    }
}

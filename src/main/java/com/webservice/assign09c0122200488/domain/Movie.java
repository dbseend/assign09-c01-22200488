package com.webservice.assign09c0122200488.domain;

import com.webservice.assign09c0122200488.dto.MovieRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String director;

    @Column(nullable = false, length = 50)
    private String category;
    
    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private Double rating;

    public Movie(MovieRequest movieRequest){
        this.title = movieRequest.title();
        this.director = movieRequest.director();
        this.releaseYear = movieRequest.releaseYear();
        this.category = movieRequest.category();
        this.rating = movieRequest.rating();
    }

    public void update(MovieRequest movieRequest){
        this.title = movieRequest.title();
        this.director = movieRequest.director();
        this.releaseYear = movieRequest.releaseYear();
        this.category = movieRequest.category();
        this.rating = movieRequest.rating();
    }
}

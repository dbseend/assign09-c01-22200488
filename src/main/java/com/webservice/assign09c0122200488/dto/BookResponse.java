package com.webservice.assign09c0122200488.dto;

import com.webservice.assign09c0122200488.domain.Book;

public record BookResponse(
        Long id,
        String title,
        String author,
        String category,
        Integer price,
        Double rating
) {
    public static BookResponse from(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getPrice(), book.getRating());
    }
}

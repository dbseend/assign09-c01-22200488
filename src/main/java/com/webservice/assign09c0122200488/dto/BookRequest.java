package com.webservice.assign09c0122200488.dto;

public record BookRequest(
        String title,
        String author,
        String category,
        Integer price,
        Double rating
) {
}

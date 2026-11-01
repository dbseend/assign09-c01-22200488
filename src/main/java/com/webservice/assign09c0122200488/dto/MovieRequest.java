package com.webservice.assign09c0122200488.dto;

public record MovieRequest(
        String title,
        String director,
        String category,
        Integer releaseYear,
        Double rating
) {
}

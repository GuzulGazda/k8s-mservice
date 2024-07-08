package kiv.tut.bookmarker.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateBookmarkRequest(
        @NotEmpty(message = "Title is required")
        String title,
        @NotEmpty(message = "Url is required")
        String url
) {
}

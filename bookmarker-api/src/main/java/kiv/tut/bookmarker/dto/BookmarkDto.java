package kiv.tut.bookmarker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDto {
    private Long id;
    private String title;
    private String url;
    private Instant createdAt;
}

package kiv.tut.bookmarker.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookmarkNotFoundException extends RuntimeException {
    private final String businessMessage;
}

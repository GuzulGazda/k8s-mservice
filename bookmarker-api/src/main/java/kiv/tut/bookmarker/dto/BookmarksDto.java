package kiv.tut.bookmarker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class BookmarksDto {

    private List<BookmarkDto> bookmarkList;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public BookmarksDto(Page<BookmarkDto> bookmarkDtoPage) {
        this.setBookmarkList(bookmarkDtoPage.getContent());
        this.setTotalElements(bookmarkDtoPage.getTotalElements());
        this.setTotalPages(bookmarkDtoPage.getTotalPages());
        this.setCurrentPage(bookmarkDtoPage.getNumber() + 1);
        this.setFirst(bookmarkDtoPage.isFirst());
        this.setLast(bookmarkDtoPage.isLast());
        this.setHasNext(bookmarkDtoPage.hasNext());
        this.setHasPrevious(bookmarkDtoPage.hasPrevious());
    }
}

package kiv.tut.bookmarker.service;

import kiv.tut.bookmarker.domain.Bookmark;
import kiv.tut.bookmarker.dto.BookmarkDto;
import kiv.tut.bookmarker.dto.BookmarksDto;
import kiv.tut.bookmarker.exception.BookmarkNotFoundException;
import kiv.tut.bookmarker.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
//@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private static final String NO_BOOKMARK_EXISTS_WITH_THE_PROVIDED_ID_D =
            "No bookmark exists with the provided Id:: %d.";
    private final BookmarkRepository repository;

    @Transactional(readOnly = true)
    public BookmarksDto findAll(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        PageRequest pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDto> bookmarkDtoPage = repository.findBookmarkDtos(pageable);
        return new BookmarksDto(bookmarkDtoPage);
    }

    @Transactional(readOnly = true)
    public Bookmark findById(Long bookmarkId) {
        return repository.findById(bookmarkId)
                .orElseThrow(() -> new BookmarkNotFoundException(
                        format(NO_BOOKMARK_EXISTS_WITH_THE_PROVIDED_ID_D, bookmarkId)
                ));
    }
    @Transactional(readOnly = true)
    public BookmarksDto searchBookmarks(Integer page, String query) {
        int pageNo = page < 1 ? 0 : page - 1;
        PageRequest pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDto> bookmarkDtoPage = repository.searchBookmars(pageable, query);
        return new BookmarksDto(bookmarkDtoPage);
    }
}

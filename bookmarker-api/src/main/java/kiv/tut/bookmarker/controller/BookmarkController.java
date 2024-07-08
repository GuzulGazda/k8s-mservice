package kiv.tut.bookmarker.controller;

import kiv.tut.bookmarker.domain.Bookmark;
import kiv.tut.bookmarker.dto.BookmarksDto;
import kiv.tut.bookmarker.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {
    private final BookmarkService service;

    @GetMapping
    public ResponseEntity<BookmarksDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "query", defaultValue = "") String query ) {
        if(query == null || query.isEmpty()){
            return ResponseEntity.ok(service.findAll(page));
        }
        return ResponseEntity.ok(service.searchBookmarks(page, query));
    }

    @GetMapping("/{bookmark-id}")
    public ResponseEntity<Bookmark> findAll(
            @PathVariable("bookmark-id") Long bookmarkId
    ) {
        return ResponseEntity.ok(service.findById(bookmarkId));
    }
}

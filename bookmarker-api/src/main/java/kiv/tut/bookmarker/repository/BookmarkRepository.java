package kiv.tut.bookmarker.repository;

import kiv.tut.bookmarker.domain.Bookmark;
import kiv.tut.bookmarker.dto.BookmarkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("""
            select new kiv.tut.bookmarker.dto.BookmarkDto(b.id, b.title, b.url, b.createdAt) from Bookmark b
            """)
    Page<BookmarkDto> findBookmarkDtos(Pageable pageable);
}

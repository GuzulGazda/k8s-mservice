package kiv.tut.bookmarker.controller;


import kiv.tut.bookmarker.domain.Bookmark;
import kiv.tut.bookmarker.repository.BookmarkRepository;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///demo"
})
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookmarkRepository repository;
    private List<Bookmark> bookmarkList;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        fillBookmarkList();
        repository.saveAll(bookmarkList);
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource({
            "1,15,2,1,true,false,true,false",
            "2,15,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
                            boolean isFirst, boolean isLast,
                            boolean hasNext, boolean hasPrevious) {
        mockMvc.perform(get("/api/v1/bookmarks?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));
    }

    private void fillBookmarkList() {
        bookmarkList = Arrays.asList(
                new Bookmark(null, "SivaLabs", "https://sivalabs.in", Instant.now()),
                new Bookmark(null, "SpringBlog", "https://spring.io/blog", Instant.now()),
                new Bookmark(null, "Quarkus", "https://quarkus.io/", Instant.now()),
                new Bookmark(null, "Micronaut", "https://micronaut.io/", Instant.now()),
                new Bookmark(null, "JOOQ", "https://www.jooq.org/", Instant.now()),
                new Bookmark(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()),
                new Bookmark(null, "Thoughts On Java", "https://thorben-janssen.com/", Instant.now()),
                new Bookmark(null, "DZone", "https://dzone.com/", Instant.now()),
                new Bookmark(null, "DevOpsBookmarks", "http://www.devopsbookmarks.com/", Instant.now()),
                new Bookmark(null, "Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()),
                new Bookmark(null, "Expressjs", "https://expressjs.com/", Instant.now()),
                new Bookmark(null, "Marcobehler", "https://www.marcobehler.com", Instant.now()),
                new Bookmark(null, "baeldung", "https://www.baeldung.com", Instant.now()),
                new Bookmark(null, "devglan", "https://www.devglan.com", Instant.now()),
                new Bookmark(null, "linuxize", "https://linuxize.com", Instant.now())
        );
    }
}

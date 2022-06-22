package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerWithMockBeanTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookServiceMock;

    @Test
    void testGettingAllBooks() throws Exception {
        when(bookServiceMock.getByAuthor(anyString())).thenReturn(List.of());

        final var result = mockMvc.perform(get("/book?author=Birgit"))
                .andExpect(status().isIAmATeapot());
    }
}

package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookRestControllerPlainTest {

    @InjectMocks
    private BookRestController bookRestController;

    @Mock
    private BookService bookServiceMock;

    @Captor
    ArgumentCaptor<String> authorCaptor;

    @Test
    void testGettingAllBooks() throws Exception {
        when(bookServiceMock.getByAuthor(authorCaptor.capture())).thenReturn(List.of());

        assertThatCode(() ->bookRestController.getByAuthor("Birgit")).isInstanceOf(BookNotFoundException.class);

        assertThat(authorCaptor.getValue()).isEqualTo("Birgit");
    }
}

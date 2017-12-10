package co.hodler.boundaries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(BookController.class)
@RunWith(SpringRunner.class)
public class BookControllerTest {
    @MockBean
    private BookRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void can_provide_books() throws Exception {
        Mockito.when(this.repo.findAll())
                .thenReturn(Collections.singletonList(new JpaBook(1L, "Name")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Name"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

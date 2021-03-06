package co.hodler.boundaries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookRepository repo;

    @InjectMocks
    BookController controller;

    @Before
    public void before_each() {
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void can_provide_books() throws Exception {
        given(this.repo.findAll())
                .willReturn(Collections.singletonList(new JpaBook(1L, "Name")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Name"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

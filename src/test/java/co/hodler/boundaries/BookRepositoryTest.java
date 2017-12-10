package co.hodler.boundaries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    public void finds_books_by_name() {
        JpaBook book = new JpaBook(null, "name");
        JpaBook frenchBook = new JpaBook(null, "nom");
        repository.save(book);
        repository.save(frenchBook);

        Collection<JpaBook> books = repository.findByName("name");

        assertThat(books, hasSize(1));
        assertThat(books, hasItem(new JpaBook(1L, "name")));
    }
}

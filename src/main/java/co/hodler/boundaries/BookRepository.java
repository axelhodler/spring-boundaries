package co.hodler.boundaries;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BookRepository extends CrudRepository<JpaBook, Long> {
    Collection<JpaBook> findByName(String name);
}

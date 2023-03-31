package edu.shoppinglist.demo;

import edu.shoppinglist.demo.repositories.ListsRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ShoppingListDemoListRepositoryTest {

    @Autowired
    private ListsRepository repo;

    @Test
    void repoIsNotNull() {
        assertThat(repo).isNotNull();
    }
}

package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.ShopRepository;

public class ProductRepositoryTest {

    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Product1", 1550);
    Product product2 = new Product(2, "Product 2", 8_000);
    Product product3 = new Product(3, "Product 3", 160_000);
    Product product4 = new Product(4, "Product 4", 2_000);
    Product product5 = new Product(5, "Product 5", 15550);
    Product product6 = new Product(6, "Product 6", 6500);

    @BeforeEach
    public void setup() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);
        repo.save(product5);
        repo.save(product6);
    }


    @Test
    public void shouldAddProduct() {
        Product newProduct = new Product(7, "New Product", 15000);
        repo.add(newProduct);

        Product[] expected = {product1, product2, product3, product4, product5, product6, newProduct};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(15);
        });
    }

    @Test
    public void shouldSaveProduct() {
        Product[] expected = {product1, product2, product3, product4, product5, product6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionSaveProduct() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product5);
        });
    }
}
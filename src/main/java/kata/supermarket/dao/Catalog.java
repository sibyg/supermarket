package kata.supermarket.dao;

import java.util.Optional;

import kata.supermarket.domain.Product;
import kata.supermarket.domain.ProductCode;

public interface Catalog {
    Optional<Product> getProduct(ProductCode code);
}

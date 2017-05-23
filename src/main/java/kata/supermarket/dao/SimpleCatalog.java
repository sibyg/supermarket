package kata.supermarket.dao;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.domain.Product;
import kata.supermarket.domain.ProductCode;
import kata.supermarket.domain.SoldBy;


public class SimpleCatalog implements Catalog {

    public Optional<Product> getProduct(ProductCode code) {
        switch (code) {
            case BEANS:
                return Optional.of(new Product.Builder()
                        .code(ProductCode.BEANS)
                        .name("Beans")
                        .price(BigDecimal.valueOf(0.50))
                        .soldBy(SoldBy.UNIT)
                        .build());
            case COKE:
                return Optional.of(new Product.Builder()
                        .code(ProductCode.COKE)
                        .name("Coke")
                        .price(BigDecimal.valueOf(0.70))
                        .soldBy(SoldBy.UNIT)
                        .build());
            case ORANGE:
                return Optional.of(new Product.Builder()
                        .code(ProductCode.ORANGE)
                        .name("Oranges")
                        .price(BigDecimal.valueOf(1.99))
                        .soldBy(SoldBy.WEIGHT)
                        .build());
            default:
                return Optional.empty();
        }
    }
}

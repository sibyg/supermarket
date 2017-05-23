package kata.supermarket.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final ProductCode code;
    private final BigDecimal price;
    private final SoldBy soldBy;

    private Product(String name, ProductCode code, BigDecimal price, SoldBy soldBy) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.soldBy = soldBy;
    }


    public String getName() {
        return name;
    }

    public ProductCode getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SoldBy getSoldBy() {
        return soldBy;
    }

    public static class Builder {
        private String name;
        private ProductCode code;
        private BigDecimal price;
        private SoldBy soldBy;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder code(ProductCode code) {
            this.code = code;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder soldBy(SoldBy soldBy) {
            this.soldBy = soldBy;
            return this;
        }


        public Product build() {
            return new Product(name, code, price, soldBy);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getName(), product.getName()) &&
                getCode() == product.getCode() &&
                Objects.equals(getPrice(), product.getPrice()) &&
                getSoldBy() == product.getSoldBy();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCode(), getPrice(), getSoldBy());
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", soldBy=" + soldBy +
                '}';
    }
}

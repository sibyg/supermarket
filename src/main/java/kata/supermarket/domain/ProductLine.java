package kata.supermarket.domain;

import java.math.BigDecimal;

public interface ProductLine {
    Product getProduct();
    BigDecimal getLineAmount();
    Integer getUnit();
    Double getWeightInKgs();
}









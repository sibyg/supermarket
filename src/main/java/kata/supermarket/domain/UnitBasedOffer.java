package kata.supermarket.domain;

import java.math.BigDecimal;

public interface UnitBasedOffer extends Offer {
    BigDecimal discount(Product product, int count);
}

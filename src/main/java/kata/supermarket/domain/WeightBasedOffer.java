package kata.supermarket.domain;

import java.math.BigDecimal;

public interface WeightBasedOffer extends Offer {
    BigDecimal discount(Product product, int count);
}

package kata.supermarket.domain;

import java.math.BigDecimal;

public class ThreeForTwoUnitBasedOffer implements UnitBasedOffer {

    private ThreeForTwoUnitBasedOffer() {
    }

    public static ThreeForTwoUnitBasedOffer instance = new ThreeForTwoUnitBasedOffer();

    @Override
    public BigDecimal discount(Product product, int count) {
        if (count < 3) return BigDecimal.ZERO;
        return product.getPrice().multiply(new BigDecimal(count / 3));
    }
}

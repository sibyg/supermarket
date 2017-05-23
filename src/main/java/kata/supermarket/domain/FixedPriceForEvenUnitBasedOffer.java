package kata.supermarket.domain;

import java.math.BigDecimal;

public class FixedPriceForEvenUnitBasedOffer implements UnitBasedOffer {

    private final BigDecimal fixedPrice;

    private FixedPriceForEvenUnitBasedOffer(BigDecimal fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public static FixedPriceForEvenUnitBasedOffer instance(BigDecimal fixedPrice) {
        return new FixedPriceForEvenUnitBasedOffer(fixedPrice);
    }

    @Override
    public BigDecimal discount(Product product, int count) {
        if (count < 2) return BigDecimal.ZERO;
        BigDecimal priceAfterOffer = fixedPrice.multiply(new BigDecimal(count / 2));
        BigDecimal actualPrice = product.getPrice().multiply(BigDecimal.valueOf(count));
        return actualPrice.subtract(priceAfterOffer);
    }
}

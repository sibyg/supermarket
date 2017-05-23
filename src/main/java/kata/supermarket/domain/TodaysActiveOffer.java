package kata.supermarket.domain;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.dao.Catalog;

public class TodaysActiveOffer implements ActiveOffer {

    private final Catalog catalog;

    public TodaysActiveOffer(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public Optional<UnitBasedOffer> getOffer(ProductCode code) {
        if (code == ProductCode.BEANS) {
            return Optional.of(ThreeForTwoUnitBasedOffer.instance);
        }

        if (code == ProductCode.COKE) {
            return Optional.of(FixedPriceForEvenUnitBasedOffer.instance(BigDecimal.ONE));
        }
        return Optional.empty();
    }
}

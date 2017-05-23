package kata.supermarket.domain;

import java.util.Optional;

public interface ActiveOffer {
    Optional<UnitBasedOffer> getOffer(ProductCode code);
}

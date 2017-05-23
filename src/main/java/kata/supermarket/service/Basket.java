package kata.supermarket.service;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kata.supermarket.domain.ActiveOffer;
import kata.supermarket.domain.ProductLine;
import kata.supermarket.domain.UnitBasedOffer;

public class Basket {
    private final List<kata.supermarket.domain.ProductLine> productLines = new ArrayList<>();

    private final ActiveOffer activeOffer;

    public Basket(ActiveOffer activeOffer) {
        this.activeOffer = activeOffer;
    }

    public void addProductLine(ProductLine productLine) {
        productLines.add(productLine);
    }

    public List<ProductLine> getProductLines() {
        return productLines;
    }

    BigDecimal getTotalCostWithDiscount(BigDecimal totalCostWithNoDiscount, BigDecimal totalDiscount) {
        return totalCostWithNoDiscount.subtract(totalDiscount);
    }

    public BigDecimal getTotalDiscount() {

//        // for each map entry, apply discount, and get total
        List<BigDecimal> discounts = new ArrayList<>();
        productLines.forEach((k -> {
            Optional<UnitBasedOffer> offer = activeOffer.getOffer(k.getProduct().getCode());
            if (offer.isPresent()) {
                BigDecimal discount = offer.get().discount(k.getProduct(), k.getUnit());
                discounts.add(discount);
            }
        }));
        return discounts.stream().reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCostWithNoDiscount() {
        return productLines.stream().map(ProductLine::getLineAmount).reduce(ZERO, BigDecimal::add);
    }
}

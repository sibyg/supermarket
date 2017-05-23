package kata.supermarket.domain;

import java.math.BigDecimal;

public final class UnitBasedProductLine implements ProductLine {

    private final int unit;
    private final Product product;

    private UnitBasedProductLine(Product otherProduct, int unit) {
        assert (otherProduct.getSoldBy() == SoldBy.UNIT);
        product = otherProduct;
        this.unit = unit;
    }

    public Integer getUnit() {
        return unit;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public BigDecimal getLineAmount() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(unit));
    }

    public Double getWeightInKgs() {
        throw new UnsupportedOperationException("Sold by Units");
    }

    public static class UnitBasedProductLineBuilder {
        private Product product;
        private int unit;

        static UnitBasedProductLineBuilder instance() {
            return new UnitBasedProductLineBuilder();
        }

        public UnitBasedProductLineBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public UnitBasedProductLineBuilder unit(int unit) {
            this.unit = unit;
            return this;
        }

        public UnitBasedProductLine build() {
            return new UnitBasedProductLine(product, unit);
        }
    }
}
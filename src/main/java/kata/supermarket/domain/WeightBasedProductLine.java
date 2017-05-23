package kata.supermarket.domain;

import static kata.supermarket.domain.SoldBy.WEIGHT;

import java.math.BigDecimal;


public final class WeightBasedProductLine implements ProductLine {
    private final double weightInKgs;
    private final Product product;

    private WeightBasedProductLine(Product otherProduct, double weightInKgs) {
        assert (otherProduct.getSoldBy() == WEIGHT);
        product = otherProduct;
        this.weightInKgs = weightInKgs;
    }

    public Double getWeightInKgs() {
        return weightInKgs;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public BigDecimal getLineAmount() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(weightInKgs));
    }

    @Override
    public Integer getUnit() {
        throw new UnsupportedOperationException("Sold by Weights");
    }

    public static class WeightBasedProductLineBuilder {
        private Product product;
        private double weightInKgs;

        public static WeightBasedProductLineBuilder instance() {
            return new WeightBasedProductLineBuilder();
        }

        public WeightBasedProductLineBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public WeightBasedProductLineBuilder weightInKgs(double weightInKgs) {
            this.weightInKgs = weightInKgs;
            return this;
        }

        public WeightBasedProductLine build() {
            return new WeightBasedProductLine(product, weightInKgs);
        }
    }
}
package kata.supermarket;

import static java.math.RoundingMode.HALF_EVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.math.BigDecimal;

import kata.supermarket.dao.Catalog;
import kata.supermarket.dao.SimpleCatalog;
import kata.supermarket.domain.ActiveOffer;
import kata.supermarket.domain.Product;
import kata.supermarket.domain.ProductCode;
import kata.supermarket.domain.ProductLine;
import kata.supermarket.domain.SoldBy;
import kata.supermarket.domain.TodaysActiveOffer;
import kata.supermarket.domain.UnitBasedProductLine;
import kata.supermarket.domain.WeightBasedProductLine;
import kata.supermarket.service.Basket;
import org.junit.Test;

/**
 * Not using any framework for acceptance test
 * Beans                0.50
 * Beans                0.50
 * Beans                0.50
 * Coke                 0.70
 * Coke                 0.70
 * Oranges
 * 0.200 kg @  £1.99/kg 0.40
 * -----
 * Sub-total            3.30
 *
 * Savings
 * Beans 3 for 2       -0.50
 * Coke 2 for £1       -0.40
 * -----
 * Total savings       -0.90
 * -------------------------
 * Total to Pay         2.40
 */
public class BasketAcceptanceTest {

    private final Catalog catalog = new SimpleCatalog();
    private final ActiveOffer activeOffer = new TodaysActiveOffer(catalog);
    private Basket basket = new Basket(activeOffer);


    @Test
    public void shouldGenerateInvoice() {
        // given
        Product beans = new Product.Builder()
                .code(ProductCode.BEANS)
                .price(BigDecimal.valueOf(0.50))
                .soldBy(SoldBy.UNIT)
                .build();
        // and
        int beansUnit = 3;
        // and
        ProductLine beanProductLine = new UnitBasedProductLine
                .UnitBasedProductLineBuilder()
                .product(beans)
                .unit(beansUnit)
                .build();
        // and
        basket.addProductLine(beanProductLine);

        // and
        Product coke = new Product.Builder()
                .code(ProductCode.COKE)
                .price(BigDecimal.valueOf(0.70))
                .soldBy(SoldBy.UNIT)
                .build();
        // and
        int cokeUnit = 2;
        // and
        ProductLine cokeProductLine = new UnitBasedProductLine
                .UnitBasedProductLineBuilder()

                .product(coke)
                .unit(cokeUnit)
                .build();
        // and
        basket.addProductLine(cokeProductLine);

        // and
        Product oranges = new Product.Builder()
                .code(ProductCode.ORANGE)
                .price(BigDecimal.valueOf(1.99))
                .soldBy(SoldBy.WEIGHT)
                .build();
        // and
        double orangesWeight = 0.2;
        // and
        ProductLine orangesProductLine = WeightBasedProductLine
                .WeightBasedProductLineBuilder.instance()
                .product(oranges)
                .weightInKgs(orangesWeight).build();
        // and
        basket.addProductLine(orangesProductLine);

        // when
        BigDecimal grossCost = basket.getTotalCostWithNoDiscount();
        // and
        BigDecimal totalDiscount = basket.getTotalDiscount();

        // then
        assertThat(totalDiscount, is(BigDecimal.valueOf(0.90)));
        // and
        BigDecimal actual = roundOff(grossCost.subtract(totalDiscount));
        BigDecimal expected = roundOff(BigDecimal.valueOf(2.40));
        assertThat(actual, is(expected));
    }

    private BigDecimal roundOff(BigDecimal price) {
        return price.setScale(2, HALF_EVEN);
    }
}

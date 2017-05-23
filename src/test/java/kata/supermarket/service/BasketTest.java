package kata.supermarket.service;

import static kata.supermarket.util.TestUtil.number;
import static kata.supermarket.util.TestUtil.randomPrice;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.domain.ActiveOffer;
import kata.supermarket.domain.FixedPriceForEvenUnitBasedOffer;
import kata.supermarket.domain.Product;
import kata.supermarket.domain.ProductCode;
import kata.supermarket.domain.ProductLine;
import kata.supermarket.domain.SoldBy;
import kata.supermarket.domain.ThreeForTwoUnitBasedOffer;
import kata.supermarket.domain.UnitBasedProductLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasketTest {
    @Mock
    private ActiveOffer activeOffer;

    private Basket basket;

    @Before
    public void setUp() {
        basket = new Basket(activeOffer);
    }

    @Test
    public void shouldAddProduct() {
        // given
        Product unitProduct = new Product.Builder()
                .code(ProductCode.COKE)
                .soldBy(SoldBy.UNIT)
                .build();
        // and
        int unit = number();

        // and
        ProductLine productLine = new UnitBasedProductLine
                .UnitBasedProductLineBuilder()
                .product(unitProduct)
                .unit(unit)
                .build();

        // when
        basket.addProductLine(productLine);

        // then
        assertThat(basket.getProductLines(), hasItems(productLine));
    }


    @Test
    public void shouldGetTotalCostWithNoDiscount() {
        // given
        Product coke = new Product.Builder()
                .code(ProductCode.COKE)
                .price(randomPrice())
                .soldBy(SoldBy.UNIT)
                .build();
        // and
        int cokeUnit = number();
        // and
        ProductLine cokeProductLine = new UnitBasedProductLine
                .UnitBasedProductLineBuilder()
                .product(coke)
                .unit(cokeUnit)
                .build();
        // and
        basket.addProductLine(cokeProductLine);

        // and
        Product beans = new Product.Builder()
                .code(ProductCode.BEANS)
                .price(randomPrice())
                .soldBy(SoldBy.UNIT)
                .build();
        // and
        int beansUnit = number();
        // and
        ProductLine beanProductLine = new UnitBasedProductLine
                .UnitBasedProductLineBuilder()
                .product(beans)
                .unit(beansUnit)
                .build();
        // and
        basket.addProductLine(beanProductLine);


        // when
        BigDecimal totalCostWithNoDiscount = basket.getTotalCostWithNoDiscount();

        // then
        assertThat(totalCostWithNoDiscount, is(coke.getPrice().multiply(BigDecimal.valueOf(cokeUnit))
                .add(beans.getPrice().multiply(BigDecimal.valueOf(beansUnit)))));
    }

    @Test
    public void shouldGetTotalDiscount() {
        // given
        Product coke = new Product.Builder()
                .code(ProductCode.COKE)
                .price(randomPrice())
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
        given(activeOffer.getOffer(ProductCode.COKE)).willReturn(Optional.of(FixedPriceForEvenUnitBasedOffer.instance(coke.getPrice())));


        // and
        Product beans = new Product.Builder()
                .code(ProductCode.BEANS)
                .price(randomPrice())
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
        given(activeOffer.getOffer(ProductCode.BEANS)).willReturn(Optional.of(ThreeForTwoUnitBasedOffer.instance));


        // when
        BigDecimal totalDiscount = basket.getTotalDiscount();

        // then
        assertThat(totalDiscount, is(coke.getPrice().add(beans.getPrice())));
    }
}
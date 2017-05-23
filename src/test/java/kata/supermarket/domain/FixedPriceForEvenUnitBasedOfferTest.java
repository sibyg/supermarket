package kata.supermarket.domain;

import static kata.supermarket.util.TestUtil.evenNumber;
import static kata.supermarket.util.TestUtil.randomPrice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FixedPriceForEvenUnitBasedOfferTest {

    @Test
    public void shouldNotProvideOfferForLessThanTwoUnits() {
        // given
        BigDecimal fixedPrice = randomPrice();
        // and
        UnitBasedOffer fixedPriceForEvenUnitBasedOffer = FixedPriceForEvenUnitBasedOffer.instance(fixedPrice);
        // and
        Product product = new Product.Builder().price(randomPrice()).build();
        // and
        int productCount = 1;

        // when
        BigDecimal discount = fixedPriceForEvenUnitBasedOffer.discount(product, productCount);

        // then
        assertThat(discount, is(BigDecimal.ZERO));

    }

    @Test
    public void shouldProvideFixedPriceForEvenProducts() {
        // given
        BigDecimal fixedPrice = randomPrice();
        // and
        UnitBasedOffer fixedPriceForEvenUnitBasedOffer = FixedPriceForEvenUnitBasedOffer.instance(fixedPrice);
        // and
        Product product = new Product.Builder().price(randomPrice()).build();
        // and
        int productCount = evenNumber();

        // when
        BigDecimal discount = fixedPriceForEvenUnitBasedOffer.discount(product, productCount);

        // then
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(productCount));
        BigDecimal actualPrice = fixedPrice.multiply(new BigDecimal(productCount / 2));
        assertThat(discount, is(totalPrice.subtract(actualPrice)));
    }
}
package kata.supermarket.domain;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static kata.supermarket.util.TestUtil.multipleOf3;
import static kata.supermarket.util.TestUtil.randomPrice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ThreeForTwoUnitBasedOfferTest {

    private final UnitBasedOffer threeForTwoUnitBasedOffer = ThreeForTwoUnitBasedOffer.instance;

    @Test
    public void shouldNotProvideOfferForLessThanThreeUnits() {
        // given
        Product product = new Product.Builder().price(randomPrice()).build();
        // and
        int productCount = 2;

        // when
        BigDecimal discount = threeForTwoUnitBasedOffer.discount(product, productCount);

        // then
        assertThat(discount, is(BigDecimal.ZERO));

    }

    @Test
    public void shouldProvideThreeForTwoUnitOfferForMultiplesOfThree() {
        // given
        Product product = new Product.Builder()
                .price(randomPrice())
                .build();
        // and
        int productCount = multipleOf3();

        // when
        BigDecimal discount = threeForTwoUnitBasedOffer.discount(product, productCount);

        // then
        assertThat(discount, is(product.getPrice().multiply(new BigDecimal(productCount / 3))));
    }
}
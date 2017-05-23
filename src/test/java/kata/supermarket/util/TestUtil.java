package kata.supermarket.util;

import java.math.BigDecimal;
import java.util.Random;

public class TestUtil {

    private final static Random RANDOM = new Random();

    public static BigDecimal randomPrice() {
        return new BigDecimal(RANDOM.nextInt(10));
    }

    public static int multipleOf3() {
        return RANDOM.nextInt(100) * 3;
    }

    public static int oddNumber() {
        return evenNumber() + 1;
    }

    public static int evenNumber() {
        return RANDOM.nextInt(100) * 2;
    }

    public static int number() {
        return RANDOM.nextInt(100);
    }

    public static BigDecimal smallerPrice(BigDecimal price) {
        return price.subtract(new BigDecimal(number()));
    }
}

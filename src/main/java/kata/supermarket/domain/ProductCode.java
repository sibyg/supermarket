package kata.supermarket.domain;

public enum ProductCode {
    BEANS("BE01"),
    COKE("CO01"),
    ORANGE("OR01");

    private final String code;

    ProductCode(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}

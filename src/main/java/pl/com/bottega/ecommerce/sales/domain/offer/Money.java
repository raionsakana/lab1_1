package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {

    private BigDecimal denomination;
    private String currency;

    public Money(BigDecimal denomination, String currency) {
        this.denomination = denomination;
        this.currency = currency;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public BigDecimal multiply(BigDecimal bigDecimal) {
        return this.denomination.multiply(bigDecimal);
    }

    public int compareTo(Money totalCost) {
        return this.denomination.compareTo(totalCost.getDenomination());
    }
}

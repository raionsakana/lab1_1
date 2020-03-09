package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Product {

    private String id;
    private Money price;
    private String name;
    private Date snapshotDate;
    private String type;

    public Product(String id, BigDecimal price, String currency, String name, Date snapshotDate, String type) {
        this.id = id;
        this.price = new Money(price, currency);
        this.name = name;
        this.snapshotDate = snapshotDate;
        this.type = type;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public Money getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
               && Objects.equals(price, product.price)
               && Objects.equals(name, product.name)
               && Objects.equals(snapshotDate, product.snapshotDate)
               && Objects.equals(type, product.type);
    }

    @Override public int hashCode() {
        return Objects.hash(id, price, name, snapshotDate, type);
    }
}

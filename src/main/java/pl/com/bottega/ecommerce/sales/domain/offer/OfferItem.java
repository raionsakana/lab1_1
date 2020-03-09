/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    // product
    private Product product;
    private int quantity;
    private Money totalCost;

    // discount
    private String discountCause;
    private Money discount;

    public OfferItem(String productId, BigDecimal productPrice, String currency, String productName, Date productSnapshotDate, String productType,
            int quantity) {
        this(productId, productPrice, currency, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String currency, String productName, Date productSnapshotDate, String productType,
            int quantity, BigDecimal discount, String discountCause) {

        this.product = new Product(productId, productPrice, currency, productName, productSnapshotDate, productType);

        this.quantity = quantity;
        this.discount = new Money(discount, currency);
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(this.discount.getDenomination());
        }

        this.totalCost = new Money(productPrice.multiply(new BigDecimal(quantity))
                                     .subtract(discountValue), currency);
    }

    public String getProductId() {
        return this.product.getId();
    }

    public Money getProductPrice() {
        return this.product.getPrice();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public Date getProductSnapshotDate() {
        return this.product.getSnapshotDate();
    }

    public String getProductType() {
        return this.product.getType();
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Money getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, discountCause, product, quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(product, other.getProduct())
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (this.product.getPrice() == null) {
            if (other.product.getPrice() != null) {
                return false;
            }
        } else if (!product.getPrice().equals(other.product.getPrice())) {
            return false;
        }
        if (product.getName() == null) {
            if (other.product.getName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.product.getName())) {
            return false;
        }

        if (product.getId() == null) {
            if (other.product.getId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.product.getId())) {
            return false;
        }
        if (product.getType() == null) {
            if (other.product.getType() != null) {
                return false;
            }
        } else if (!product.getType().equals(other.product.getType())) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost.getDenomination();
            min = other.totalCost.getDenomination();
        } else {
            max = other.totalCost.getDenomination();
            min = totalCost.getDenomination();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}

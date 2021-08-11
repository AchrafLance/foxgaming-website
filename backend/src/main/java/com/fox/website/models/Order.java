package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @NotEmpty
    private String size;

    @NotEmpty
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="card_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public Order(Integer quantity, String size, Cart cart, Product product) {
        this.quantity = quantity;
        this.size = size;
        this.cart = cart;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wishlist )) return false;
        return orderId != null && orderId.equals(((Wishlist) o).getWishlistId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name="card_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;

    public Order(String size, Integer quantity, Product product, Cart cart){
        this.size = size;
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

    public String toString(){
        return "";
    }
}

package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotEmpty
    private String productName;

    @NotEmpty
    private Integer productPrice;

    @NotEmpty
    private String productDescription;

    @NotEmpty
    private String productIcon;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    @ManyToMany
    @JoinTable(name="wishlist_products",
            joinColumns = @JoinColumn(name= "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id"))
    @JsonIgnore
    private Set<Wishlist> wishlists = new HashSet<>();

    @OneToMany(mappedBy = "product")

    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    public String toString(){
        return "";
    }

}

package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishlistId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="wishlist_products",
            joinColumns = @JoinColumn(name= "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id"))
    private Set<Product> products = new HashSet<>();

    public Wishlist(User user){
        this.user = user;
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.getWishlists().add(this);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
        product.getWishlists().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wishlist )) return false;
        return wishlistId != null && wishlistId.equals(((Wishlist) o).getWishlistId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "wishlists")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public Wishlist(User user, HashSet<Product> products){
        this.user = user;
        this.products = products;
    }

    public String toString(){
        return "";
    }

}

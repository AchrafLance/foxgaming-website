package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    public Cart(User savedUser, HashSet<Order> orders) {
        this.user = savedUser;
        this.orders = orders;
    }

    public String toString(){
        return "";
    }







}

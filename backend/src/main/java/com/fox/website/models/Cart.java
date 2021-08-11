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

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    @JsonIgnore
//    private User user;

    @OneToMany(mappedBy = "cart",
               cascade = CascadeType.ALL,
                orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

//    public Cart(User savedUser) {
//        this.user = savedUser;
//    }

    public void addOrder(Order order){
        orders.add(order);
        order.setCart(this);
    }

    public void removeOrder(Order order){
        orders.remove(order);
        order.setCart(null);
    }



//    public String toString(){
//        return "";
//    }







}

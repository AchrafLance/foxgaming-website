package com.fox.website.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name="last_name")
    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min=3, message = "length must be more than 3")
    private String password;

    @OneToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

    public String toString(){
        return "";
    }

}

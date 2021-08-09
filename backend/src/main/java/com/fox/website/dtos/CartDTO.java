package com.fox.website.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable {
    public Long cartId;
    public Long userId;
}

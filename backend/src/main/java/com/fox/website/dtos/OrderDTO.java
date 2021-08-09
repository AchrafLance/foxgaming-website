package com.fox.website.dtos;

import com.fox.website.models.Order;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    public Integer quantity;
    public String size;
    public Long productId;
    public Long userId;
}

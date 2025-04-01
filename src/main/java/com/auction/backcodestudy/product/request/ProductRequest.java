package com.auction.backcodestudy.product.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal startPrice;
	private LocalDateTime localDateTime;
}

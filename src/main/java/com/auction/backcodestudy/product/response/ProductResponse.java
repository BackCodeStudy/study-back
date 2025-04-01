package com.auction.backcodestudy.product.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.auction.backcodestudy.product.entity.Product;

import lombok.Data;

@Data
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private BigDecimal startingPrice;
	private LocalDateTime localDateTime;
	private String winnerName;
	private String status;
	private LocalDateTime createdAt;

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.startingPrice = product.getStartingPrice();
		this.localDateTime = product.getLocalDateTime();
		this.winnerName = product.getWinner() != null ? product.getWinner().getUsername() : null;
		this.status = calculateStatus(product);
		this.createdAt = getCreatedAt();
	}

	private String calculateStatus(Product product) {
		return product.getLocalDateTime().isBefore(LocalDateTime.now()) ? "Finished" : "Ongoing";
	}
}

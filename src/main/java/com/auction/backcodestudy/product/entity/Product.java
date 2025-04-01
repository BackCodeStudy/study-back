package com.auction.backcodestudy.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private BigDecimal startingPrice;
	private LocalDateTime localDateTime;
	private String status;
	private LocalDateTime createdAt;
	private String productImage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "winner_id")

	/* 낙찰자 */
	private User winner;
}

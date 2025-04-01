package com.auction.backcodestudy.product.request;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 상품수정DTO
 */
@Data
public class ProductUpdateRequest {
	private String name;
	/* 상품 사진을 URL이나 파일경로 등으로 처리함*/
	private String productImage;
	private BigDecimal startPrice;
}

package com.auction.backcodestudy.product.request;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 상품수정DTO
 */
@Data
public class ProductUpdateRequest {

	/* 상품 이름 수정*/
	private String fixName;

	/* 상품 사진을 URL이나 파일경로 수정*/
	private String fixProductImage;

	/* 상품 가격 수정*/
	private BigDecimal fixPrice;
}

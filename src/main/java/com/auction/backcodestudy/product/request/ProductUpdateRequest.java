package com.auction.backcodestudy.product.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 상품수정DTO
 */
@Data
public class ProductUpdateRequest {

	/* 상품 이름 수정*/
	@NotBlank(message = "상품 이름은 필수 입력 항목입니다.")
	private String fixName;

	/* 상품 사진을 URL이나 파일경로 수정*/
	@NotNull(message = "시작 가격은 필수 입력 항목입니다.")
	@DecimalMin(value = "0.0", inclusive = false, message = "시작 가격은 0보다 커야 합니다.")
	private String fixProductImage;

	/* 상품 가격 수정*/
	@NotBlank(message = "상품 이미지 URL은 필수 입력 항목입니다.")
	private BigDecimal fixPrice;
}

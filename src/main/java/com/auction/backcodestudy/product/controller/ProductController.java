package com.auction.backcodestudy.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.backcodestudy.product.request.ProductRequest;
import com.auction.backcodestudy.product.response.ProductResponse;
import com.auction.backcodestudy.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	/**
	 *
	 * @param productRequest
	 * @return
	 */
	@PostMapping
	@Operation(summary = "상품 등록", description = "경매 상품을 등록하는 API")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "상품 등록 성공"),
		@ApiResponse(responseCode = "400", description = "잘못된 요청")
	})
	public ResponseEntity<ProductResponse> createProduct(
		@RequestBody @Parameter(description = "상품 등록 정보") ProductRequest productRequest) {
		ProductResponse productResponse = productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@Operation(summary = "상품 상세 조회", description = "특정 경매 상품의 상세 정보를 조회하는 API")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "상품 상세 조회 성공"),
		@ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
	})
	public ResponseEntity<ProductResponse> getProduct(@PathVariable @Parameter(description = "상품 ID") Long id) {
		ProductResponse productResponse = productService.getProduct(id);
		return ResponseEntity.ok(productResponse);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping
	@Operation(summary = "모든 상품 목록 조회", description = "모든 경매 상품의 목록을 조회하는 API")
	@ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	/**
	 *
	 * @param productId
	 * @param winnerId
	 * @return
	 */
	@PostMapping("/{productId}/assign-winner/{winnerId}")
	@Operation(summary = "낙찰자 할당", description = "경매 상품에 낙찰자를 할당하는 API")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "낙찰자 할당 성공"),
		@ApiResponse(responseCode = "404", description = "상품 또는 사용자를 찾을 수 없음")
	})
	public ResponseEntity<ProductResponse> assignWinner(@PathVariable @Parameter(description = "상품 ID") Long productId,
		@PathVariable @Parameter(description = "사용자 ID") Long winnerId) {
		ProductResponse productResponse = productService.assignWinner(productId, winnerId);
		return ResponseEntity.ok(productResponse);
	}
}

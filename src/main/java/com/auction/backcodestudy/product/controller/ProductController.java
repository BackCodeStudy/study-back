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
import com.auction.backcodestudy.product.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
	private final ProductServiceImpl productServiceImpl;

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
		try {
			ProductResponse productResponse = productServiceImpl.createProduct(productRequest);
			log.info("상품 생성 성공={}", productResponse);
			return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
		} catch (Exception e) {
			log.info("상품 생성 실패={}", e.getCause(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
		try {
			ProductResponse productResponse = productServiceImpl.getProduct(id);
			log.info("상품 상세 조회 성공={}", productResponse);
			return ResponseEntity.ok(productResponse);
		} catch (Exception e) {
			log.info("상품 상세 조회 실패", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 *
	 * @return
	 */
	@GetMapping
	@Operation(summary = "모든 상품 목록 조회", description = "모든 경매 상품의 목록을 조회하는 API")
	@ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		try {
			List<ProductResponse> products = productServiceImpl.getAllProducts();
			log.info("모든 상품 목록 조회 성공={}", products);
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			log.info("모든 상품 목록 조회 실패={}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
		try {
			ProductResponse productResponse = productServiceImpl.assignWinner(productId, winnerId);
			log.info("낙찰자 할당 성공={}", productResponse);
			return ResponseEntity.ok(productResponse);
		} catch (Exception e) {
			log.info("낙찰자 할당 실패={}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

package com.auction.backcodestudy.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auction.backcodestudy.product.request.ProductRequest;
import com.auction.backcodestudy.product.request.ProductUpdateRequest;
import com.auction.backcodestudy.product.response.ProductResponse;

@Service
public interface ProductService {

	ProductResponse createProduct(ProductRequest productRequest);

	public ProductResponse getProduct(Long id);

	public List<ProductResponse> getAllProducts();

	ProductResponse assignWinner(Long productId, Long winnerId);

	/**
	 * 상품 수정 기능 추가
	 * 상품을 수정할 (productId)와 수정할 데이터를 담은(ProductUpdateRequest)
	 */
	ProductResponse updateProduct(Long productId, ProductUpdateRequest productUpdateRequest);

}

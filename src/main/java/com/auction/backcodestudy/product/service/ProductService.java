package com.auction.backcodestudy.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auction.backcodestudy.product.request.ProductRequest;
import com.auction.backcodestudy.product.response.ProductResponse;

@Service
public interface ProductService {
	ProductResponse createProduct(ProductRequest productRequest);

	ProductResponse getProduct(Long id);

	List<ProductResponse> getAllProducts();

	ProductResponse assignWinner(Long productId, Long winnerId);
}

package com.auction.backcodestudy.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.auction.backcodestudy.product.entity.Product;
import com.auction.backcodestudy.product.entity.User;
import com.auction.backcodestudy.product.repository.ProductRepository;
import com.auction.backcodestudy.product.repository.UserRepository;
import com.auction.backcodestudy.product.request.ProductRequest;
import com.auction.backcodestudy.product.request.ProductUpdateRequest;
import com.auction.backcodestudy.product.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setStartingPrice(productRequest.getStartPrice());
		product.setLocalDateTime(productRequest.getLocalDateTime());

		Product savedProduct = productRepository.save(product);
		return new ProductResponse(savedProduct);
	}

	@Override
	public ProductResponse getProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Product not found"));
		return new ProductResponse(product);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream()
			.map(ProductResponse::new)
			.collect(Collectors.toList());
	}

	@Override
	public ProductResponse assignWinner(Long productId, Long winnerId) {
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new RuntimeException("Product not found"));

		User winner = userRepository.findById(winnerId)
			.orElseThrow(() -> new RuntimeException("User not found"));

		product.setWinner(winner);
		productRepository.save(product);
		return new ProductResponse(product);
	}

	/**
	 * 상품 수정 기능
	 * @param productId
	 * @param productUpdateRequest
	 * @return
	 */
	@Override
	public ProductResponse updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {

		//Repo 조회
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (!optionalProduct.isPresent()) {
			throw new RuntimeException("Product not found with id: " + productId);
		}
		Product product = optionalProduct.get();
		product.setName(productUpdateRequest.getFixName());
		product.setStartingPrice(productUpdateRequest.getFixPrice());
		product.setProductImage(productUpdateRequest.getFixProductImage());

		Product saveProduct = productRepository.save(product);
		return new ProductResponse(saveProduct);
	}
}

package com.auction.backcodestudy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.backcodestudy.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.auction.backcodestudy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.backcodestudy.product.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

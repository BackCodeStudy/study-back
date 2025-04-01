package com.auction.backcodestudy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.backcodestudy.product.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

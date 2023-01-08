package org.spring.repository.impl;

import org.spring.entity.Product;
import org.spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    @Qualifier("customJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveProduct(Product product) {
        String sql = "INSERT INTO PRODUCT VALUES (?, ?)";

        Object[] args = {
          product.getId(),
          product.getName()
        };

        jdbcTemplate.update(sql, args);
    }
}

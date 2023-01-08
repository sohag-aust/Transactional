package org.spring.repository.impl;

import org.spring.entity.Product;
import org.spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    @Qualifier("customJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

//    @Transactional(propagation = Propagation.REQUIRES_NEW) // this will create a new connection everytime
    // REQUIRES_NEW : suspend an active transaction (already created by service method),if any. create a new transaction, and execute the code under it
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

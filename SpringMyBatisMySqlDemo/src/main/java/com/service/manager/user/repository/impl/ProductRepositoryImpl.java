package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.mapper.ProductMapper;
import com.service.manager.user.repository.ProductRepository;


@Repository
public class ProductRepositoryImpl implements ProductRepository{

	@Autowired
	ProductMapper productMapper;
	
	@Override
	public int insertProduct(Product product) {
		return productMapper.insert(product);
	}

}

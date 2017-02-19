package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.ProductCategory;
import com.service.manager.user.persistence.mapper.ProductCategoryMapper;
import com.service.manager.user.repository.ProductCategoryRepository;


@Repository
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository{

	@Autowired
	ProductCategoryMapper productCategoryMapper;

	@Override
	public int insertProductCategory(ProductCategory productCategory) {
		return productCategoryMapper.insert(productCategory);
	}

}

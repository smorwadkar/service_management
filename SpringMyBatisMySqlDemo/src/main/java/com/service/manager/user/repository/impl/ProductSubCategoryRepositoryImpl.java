package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.ProductSubCategory;
import com.service.manager.user.persistence.mapper.ProductSubCategoryMapper;
import com.service.manager.user.repository.ProductSubCategoryRepository;


@Repository
public class ProductSubCategoryRepositoryImpl implements ProductSubCategoryRepository{

	@Autowired
	ProductSubCategoryMapper productSubCategoryMapper;

	@Override
	public int insertProductSubCategory(ProductSubCategory productSubCategory) {
		 return productSubCategoryMapper.insert(productSubCategory); 
	}

	
}

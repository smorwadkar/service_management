package com.service.manager.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.manager.user.persistence.ProductType;
import com.service.manager.user.persistence.mapper.ProductTypeMapper;
import com.service.manager.user.repository.ProductTypeRepository;


@Repository
public class ProductTypeRepositoryImpl implements ProductTypeRepository{

	@Autowired
	ProductTypeMapper productTypeMapper;

	@Override
	public int insertProductType(ProductType productType) {
		return productTypeMapper.insert(productType);
	}
	

}

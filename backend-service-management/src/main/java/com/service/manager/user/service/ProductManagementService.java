package com.service.manager.user.service;

import java.util.List;

import com.service.manager.user.dto.ProductCategoryDetails;
import com.service.manager.user.dto.ProductSubCategoryDetails;
import com.service.manager.user.dto.ProductTypeDetails;
import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.ProductCategory;
import com.service.manager.user.persistence.ProductSubCategory;
import com.service.manager.user.persistence.ProductType;

public interface ProductManagementService {
	public void addProductType(ProductTypeDetails productTypeDetails);
	
	public void addProductSubCategory(ProductSubCategoryDetails  productSubCategoryDetails);
	
	public void addProductCategory(ProductCategoryDetails  productCategoryDetails);
	
	public Product addProduct(String categoryName,String subCategoryName,
			String typeName);
	
	public List<ProductCategory> getAllCategories();
	
	public List<ProductSubCategory> getAllSubCategories();
	
	public List<ProductSubCategory> getSubCategories(String categoryId);
	
	public List<ProductType> getProductTypes(String subCategoryId);
	
	public Product getProductFromDetails();

	public List<ProductType> getProductTypes();
}

package com.service.manager.user.dto;

import com.service.manager.user.persistence.ProductCategory;
import com.service.manager.user.persistence.ProductSubCategory;
import com.service.manager.user.persistence.ProductType;

public class FullProductDetails {
	private ProductCategory productCategory;
	private ProductSubCategory productSubCategory;
	private ProductType productType;
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public ProductSubCategory getProductSubCategory() {
		return productSubCategory;
	}
	public void setProductSubCategory(ProductSubCategory productSubCategory) {
		this.productSubCategory = productSubCategory;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
}	

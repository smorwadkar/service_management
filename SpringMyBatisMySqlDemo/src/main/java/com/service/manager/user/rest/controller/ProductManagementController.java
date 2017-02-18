package com.service.manager.user.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.manager.user.dto.ProductCategoryDetails;
import com.service.manager.user.dto.ProductDetails;
import com.service.manager.user.dto.ProductSubCategoryDetails;
import com.service.manager.user.dto.ProductTypeDetails;
import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.ProductCategory;
import com.service.manager.user.persistence.ProductSubCategory;
import com.service.manager.user.persistence.ProductType;
import com.service.manager.user.service.ProductManagementService;

@RestController
@RequestMapping("/api/product")
public class ProductManagementController {
	
	@Autowired
	ProductManagementService productManagementService;
	
	@RequestMapping(value = "/addProductType", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void addProductType(@RequestBody ProductTypeDetails productTypeDetails){
		productManagementService.addProductType(productTypeDetails);
	}
	
	@RequestMapping(value = "/addProductSubCategory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void addProductSubCategory(@RequestBody ProductSubCategoryDetails  productSubCategoryDetails){
		productManagementService.addProductSubCategory(productSubCategoryDetails);
	}
	
	@RequestMapping(value = "/addProductCategory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void addProductCategory(@RequestBody ProductCategoryDetails  productCategoryDetails){
		productManagementService.addProductCategory(productCategoryDetails);
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void addProduct(@RequestParam("categoryName") String categoryName,@RequestParam("subCategoryName") String subCategoryName,
			@RequestParam("typeName") String typeName){
		productManagementService.addProduct(categoryName, subCategoryName, typeName);
	}
	
	@RequestMapping(value = "/allCategories" ,method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductCategory> getAllCategories(){
		return productManagementService.getAllCategories();
	}
	
	@RequestMapping(value = "/allSubCategories/{categoryId}" ,method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductSubCategory> getSubCategories(@PathVariable("categoryId") String categoryId){
		return productManagementService.getSubCategories(categoryId);
	}
	
	@RequestMapping(value = "/allProductTypes/{subCategoryId}" ,method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductType> getProductTypes(@PathVariable("subCategoryId") String subCategoryId){
		return productManagementService.getProductTypes(subCategoryId);
	}
	
	private ProductType populateProductType(ProductTypeDetails productTypeDetails){
		ProductType productType = new ProductType();
		productType.setTypeName(productTypeDetails.getProductTypeName());
		return productType;
	}
	
	private ProductSubCategory populateProductSubCategory(ProductSubCategoryDetails producytSubCategoryDetails){
		ProductSubCategory productSubCategory = new ProductSubCategory();
		productSubCategory.setSubCategoryName(producytSubCategoryDetails.getProductSubCategoryName());
		return productSubCategory;
	}
	
	private ProductCategory populateProductCategory(ProductCategoryDetails productCategoryDetails){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName(productCategoryDetails.getProductCategoryName());
		return productCategory;
	}
	
	private Product populateProduct(ProductDetails productDetails){
		Product product = new Product();
		product.setCategoryId(productDetails.getCategoryId());
		product.setSubCategoryId(productDetails.getSubCategoryId());
		product.setTypeId(productDetails.getProductTypeId());
		
		return product;
	}
}

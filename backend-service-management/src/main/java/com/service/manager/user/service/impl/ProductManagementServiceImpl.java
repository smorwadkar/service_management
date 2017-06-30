package com.service.manager.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.ProductCategoryDetails;
import com.service.manager.user.dto.ProductDetails;
import com.service.manager.user.dto.ProductSubCategoryDetails;
import com.service.manager.user.dto.ProductTypeDetails;
import com.service.manager.user.persistence.CategorySubCategoryExample;
import com.service.manager.user.persistence.CategorySubCategoryKey;
import com.service.manager.user.persistence.Product;
import com.service.manager.user.persistence.ProductCategory;
import com.service.manager.user.persistence.ProductCategoryExample;
import com.service.manager.user.persistence.ProductExample;
import com.service.manager.user.persistence.ProductSubCategory;
import com.service.manager.user.persistence.ProductSubCategoryExample;
import com.service.manager.user.persistence.ProductType;
import com.service.manager.user.persistence.ProductTypeExample;
import com.service.manager.user.persistence.SubCategoryTypeExample;
import com.service.manager.user.persistence.SubCategoryTypeKey;
import com.service.manager.user.persistence.mapper.CategorySubCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductMapper;
import com.service.manager.user.persistence.mapper.ProductSubCategoryMapper;
import com.service.manager.user.persistence.mapper.ProductTypeMapper;
import com.service.manager.user.persistence.mapper.SubCategoryTypeMapper;
import com.service.manager.user.repository.ProductCategoryRepository;
import com.service.manager.user.repository.ProductRepository;
import com.service.manager.user.repository.ProductSubCategoryRepository;
import com.service.manager.user.repository.ProductTypeRepository;
import com.service.manager.user.service.ProductManagementService;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

	@Autowired
	ProductTypeRepository productTypeRepository;

	@Autowired
	ProductSubCategoryRepository productSubCategoryRepository;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductTypeMapper productTypeMapper;

	@Autowired
	ProductSubCategoryMapper productSubCategoryMapper;

	@Autowired
	ProductCategoryMapper productCategoryMapper;

	@Autowired
	CategorySubCategoryMapper categorySubCategoryMapper;

	@Autowired
	SubCategoryTypeMapper subCategoryTypeMapper;

	@Autowired
	ProductMapper productMapper;

	@Override
	public void addProductType(ProductTypeDetails productTypeDetails) {
		productTypeRepository
				.insertProductType(populateProductType(productTypeDetails));
	}

	@Override
	public void addProductSubCategory(
			ProductSubCategoryDetails productSubCategoryDetails) {
		productSubCategoryRepository
				.insertProductSubCategory(populateProductSubCategory(productSubCategoryDetails));
	}

	@Override
	public void addProductCategory(ProductCategoryDetails productCategoryDetails) {
		productCategoryRepository
				.insertProductCategory(populateProductCategory(productCategoryDetails));
	}

	@Override
	public Product addProduct(String categoryName, String subCategoryName,
			String typeName) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andTypeNameLike(typeName);

		List<ProductType> productTypes = productTypeMapper
				.selectByExample(productTypeExample);
		ProductTypeDetails productTypeDetails = new ProductTypeDetails();
		productTypeDetails.setProductTypeName(typeName);
		if (productTypes.isEmpty()) {
			// Insert Product Type
			productTypeMapper.insert(populateProductType(productTypeDetails));
			productTypes = productTypeMapper
					.selectByExample(productTypeExample);
		}

		ProductSubCategoryExample productSubCategoryExample = new ProductSubCategoryExample();
		productSubCategoryExample.createCriteria().andSubCategoryNameLike(
				subCategoryName);

		List<ProductSubCategory> subCategories = productSubCategoryMapper
				.selectByExample(productSubCategoryExample);

		ProductSubCategoryDetails productSubCategoryDetails = new ProductSubCategoryDetails();
		productSubCategoryDetails.setProductSubCategoryName(subCategoryName);
		if (subCategories.isEmpty()) {

			// insert Product Sub Category
			productSubCategoryMapper
					.insert(populateProductSubCategory(productSubCategoryDetails));
			subCategories = productSubCategoryMapper
					.selectByExample(productSubCategoryExample);
		}

		SubCategoryTypeExample subCategoryTypeExample = new SubCategoryTypeExample();
		subCategoryTypeExample
				.createCriteria()
				.andSubCategoryIdEqualTo(
						subCategories.get(0).getSubCategoryId())
				.andTypeIdEqualTo(productTypes.get(0).getTypeId());
		List<SubCategoryTypeKey> subCategoryTypes = subCategoryTypeMapper
				.selectByExample(subCategoryTypeExample);

		if (subCategoryTypes.isEmpty()) {
			SubCategoryTypeKey subCategoryTypeKey = new SubCategoryTypeKey();
			subCategoryTypeKey.setTypeId(productTypes.get(0).getTypeId());
			subCategoryTypeKey.setSubCategoryId(subCategories.get(0)
					.getSubCategoryId());

			subCategoryTypeMapper.insert(subCategoryTypeKey);
		}

		// insert category

		ProductCategoryExample productCategoryExample = new ProductCategoryExample();
		productCategoryExample.createCriteria().andCategoryNameLike(
				categoryName);

		List<ProductCategory> categories = productCategoryMapper
				.selectByExample(productCategoryExample);

		if (categories.isEmpty()) {

			ProductCategoryDetails productCategoryDetails = new ProductCategoryDetails();
			productCategoryDetails.setProductCategoryName(categoryName);

			productCategoryMapper
					.insert(populateProductCategory(productCategoryDetails));

			categories = productCategoryMapper
					.selectByExample(productCategoryExample);
		}

		CategorySubCategoryExample categorySubCategoryExample = new CategorySubCategoryExample();
		categorySubCategoryExample
				.createCriteria()
				.andCategoryIdEqualTo(categories.get(0).getCategoryId())
				.andSubCategoryIdEqualTo(
						subCategories.get(0).getSubCategoryId());

		List<CategorySubCategoryKey> categorySubCategoryKeys = categorySubCategoryMapper
				.selectByExample(categorySubCategoryExample);

		if (categorySubCategoryKeys.isEmpty()) {
			CategorySubCategoryKey categorySubCategoryKey = new CategorySubCategoryKey();
			categorySubCategoryKey.setSubCategoryId(subCategories.get(0)
					.getSubCategoryId());
			categorySubCategoryKey.setCategoryId(categories.get(0)
					.getCategoryId());

			categorySubCategoryMapper.insert(categorySubCategoryKey);
		}

		ProductExample productExample = new ProductExample();
		productExample
				.createCriteria()
				.andCategoryIdEqualTo(categories.get(0).getCategoryId())
				.andSubCategoryIdEqualTo(
						subCategories.get(0).getSubCategoryId())
				.andTypeIdEqualTo(productTypes.get(0).getTypeId());

		List<Product> productCreated = productMapper
				.selectByExample(productExample);

		if (productCreated.isEmpty()) {
			ProductDetails productDetails = new ProductDetails();
			productDetails.setProductTypeId(productTypes.get(0).getTypeId());
			productDetails.setSubCategoryId(subCategories.get(0)
					.getSubCategoryId());
			productDetails.setCategoryId(categories.get(0).getCategoryId());

			productRepository.insertProduct(populateProduct(productDetails));
			
			productCreated = productMapper.selectByExample(productExample);
		}

		return productCreated.get(0);
	}

	@Override
	public List<ProductCategory> getAllCategories() {
		return productCategoryMapper.getAllCategories();
	}

	@Override
	public List<ProductSubCategory> getAllSubCategories() {
		return productSubCategoryMapper.getAllSubCategories();
	}
	
	@Override
	public List<ProductSubCategory> getSubCategories(String categoryId) {
		return productSubCategoryMapper.getCategoriesByCategoryId(Integer
				.parseInt(categoryId));
	}

	@Override
	public List<ProductType> getProductTypes() {
		return productTypeMapper.getAllProductTypes();
	}
	
	@Override
	public List<ProductType> getProductTypes(String subCategoryId) {
		return productTypeMapper.getProductTypesBySubCategoryId(Integer
				.parseInt(subCategoryId));
	}

	private ProductType populateProductType(
			ProductTypeDetails productTypeDetails) {
		ProductType productType = new ProductType();
		productType.setTypeName(productTypeDetails.getProductTypeName());
		return productType;
	}

	private ProductSubCategory populateProductSubCategory(
			ProductSubCategoryDetails producytSubCategoryDetails) {
		ProductSubCategory productSubCategory = new ProductSubCategory();
		productSubCategory.setSubCategoryName(producytSubCategoryDetails
				.getProductSubCategoryName());
		return productSubCategory;
	}

	private ProductCategory populateProductCategory(
			ProductCategoryDetails productCategoryDetails) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName(productCategoryDetails
				.getProductCategoryName());
		return productCategory;
	}

	private Product populateProduct(ProductDetails productDetails) {
		Product product = new Product();
		product.setCategoryId(productDetails.getCategoryId());
		product.setSubCategoryId(productDetails.getSubCategoryId());
		product.setTypeId(productDetails.getProductTypeId());

		return product;
	}

	@Override
	public Product getProductFromDetails() {
		// TODO Auto-generated method stub
		return null;
	}
}

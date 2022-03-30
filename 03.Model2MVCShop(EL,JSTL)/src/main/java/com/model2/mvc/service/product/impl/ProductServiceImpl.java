package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.domain.Product;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		productDAO = new ProductDAO();
	}



	@Override
	public Product addProduct(Product product) throws Exception{
		// TODO Auto-generated method stub
	       productDAO.insertProduct(product);
	       return product;
	}

	@Override
	public Product getProduct(int prodNO) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.findProduct(prodNO);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception{
		// TODO Auto-generated method stub
		return productDAO.getProductList(search);
	}

	@Override
	public Product updateProduct(Product product)  throws Exception {
		// TODO Auto-generated method stub
		productDAO.updateProduct(product);
		return product;
	}
	
	

	

	
}

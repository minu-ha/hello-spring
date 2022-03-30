package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;



public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
							HttpServletResponse response) throws Exception {
	
		Product product=new Product();
		
		product.setProdName(request.getParameter("PROD_NAME"));
		product.setProdDetail(request.getParameter("PROD_DETAIL"));
		product.setManuDate(request.getParameter("MANUFACTURE_DAY"));
		product.setPrice(Integer.parseInt(request.getParameter("PRICE")));
		product.setFileName(request.getParameter("IMAGE_FILE"));
		
		System.out.println(product);
		
		ProductService service= new ProductServiceImpl();
		service.addProduct(product);
		
		return "redirect:/user/loginView.jsp";
	}
}
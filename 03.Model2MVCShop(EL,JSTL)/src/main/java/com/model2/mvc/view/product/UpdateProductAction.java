package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int prodNO = Integer.parseInt(request.getParameter("prodNo"));
		Product product=new Product();
		
		product.setProdNo(prodNO);
		product.setProdName(request.getParameter("PROD_NAME"));
		product.setProdDetail(request.getParameter("PROD_DETAIL"));
		product.setManuDate(request.getParameter("MANUFACTURE_DAY"));
		product.setPrice(Integer.parseInt(request.getParameter("PRICE")));
		product.setFileName(request.getParameter("IMAGE_FILE"));
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(product);
		
		HttpSession session = request.getSession();
		String sessionId = String.valueOf(((Product)session.getAttribute("product")).getProdNo());
	
		if(sessionId.equals(prodNO)){
			session.setAttribute("product", product);
		}
		
		return "redirect:/getProduct.do?prod_NO="+prodNO;
	}
}
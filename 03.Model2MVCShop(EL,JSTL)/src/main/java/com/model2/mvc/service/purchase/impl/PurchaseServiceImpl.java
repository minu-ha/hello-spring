package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.*;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDAO;


	@Override
	public Purchase addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		
		purchaseDAO.insertPurchase(purchase);
		return purchase;
	}

	@Override
	public Purchase getPurchase(int tranNO) throws Exception {
		
		return purchaseDAO.findPurchase(tranNO);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDAO.getPurchaseList(search);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDAO.updatePurchase(purchase);
		return purchase;
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

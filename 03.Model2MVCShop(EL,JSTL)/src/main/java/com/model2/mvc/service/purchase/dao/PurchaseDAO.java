package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.*;
import com.model2.mvc.service.user.dao.*;

public class PurchaseDAO {

	
	public void insertPurchase(Purchase purchase) throws Exception{
		
		Product product = new Product();
		User user = new User();
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into transaction values (seq_product_prod_no,?,?,?,?,sysdata,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, product.getProdNo());
		stmt.setString(2, product.getProdName());
		stmt.setString(3, product.getProdDetail());
		stmt.setString(4, product.getManuDate());
		stmt.setString(5, purchase.getReceiverName());
		stmt.setString(6, purchase.getPaymentOption());
		stmt.setString(7, user.getUserName());
		stmt.setString(8, purchase.getReceiverName());
		stmt.setString(8, purchase.getReceiverPhone());
		stmt.setString(9, purchase.getDivyAddr());
		stmt.setString(8, purchase.getDivyRequest());
		stmt.setString(8, purchase.getDivyDate());
		
		stmt.executeUpdate();
		
		con.close();
	}
	
	public Purchase findPurchase(int tranNO) throws Exception{
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from transaction where tran_no=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNO);
		
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchase = null;
		UserDAO userDAO = new UserDAO();
		
		
		while (rs.next()) {
			purchase = new Purchase();
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setBuyer(userDAO.findUser(rs.getString("BUYER_ID")));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setOrderDate(rs.getDate("ORDER_DATA"));
			
		}
		rs.close();
		stmt.close();
		con.close();
		return purchase;	
	}
	
	public HashMap<String, Object> getPurchaseList(Search search) throws Exception{
		// TODO Auto-generated method stub
		Map<String , Object>  map = new HashMap<String, Object>();
        Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT user_id ,  user_name , email  FROM  users ";
		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE user_id = '" + search.getSearchKeyword()+"'";
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE user_name ='" + search.getSearchKeyword()+"'";
			}
		}
		sql += " ORDER BY user_id";
		
		System.out.println("UserDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("UserDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<User> list = new ArrayList<User>();
		
		while(rs.next()){
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("email"));
			list.add(user);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);
		
		return null;
		
	}
	
	
	
	
	public HashMap<String, Object> getSaleList(Search search) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void updatePurchase(Purchase purchase) throws Exception{
		
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		
	}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
		private int getTotalCount(String sql) throws Exception {
			
			sql = "SELECT COUNT(*) "+
			          "FROM ( " +sql+ ") countTable";
			
			Connection con = DBUtil.getConnection();
			PreparedStatement pStmt = con.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			int totalCount = 0;
			if( rs.next() ){
				totalCount = rs.getInt(1);
			}
			
			pStmt.close();
			con.close();
			rs.close();
			
			return totalCount;
		}
		
		// 게시판 currentPage Row 만  return 
		private String makeCurrentPageSql(String sql , Search search){
			sql = 	"SELECT * "+ 
						"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
										" 	FROM (	"+sql+" ) inner_table "+
										"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
						"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
			
			System.out.println("UserDAO :: make SQL :: "+ sql);	
			
			return sql;
		}
	
}

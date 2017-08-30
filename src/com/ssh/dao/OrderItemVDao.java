package com.ssh.dao;

import com.ssh.model.Book;
import com.ssh.model.OrderItemV;

public class OrderItemVDao {
	protected BookDao bd;
	
	public OrderItemV getBookByBookId(int bid){
		OrderItemV res = new OrderItemV();
		Book tmp =  bd.getBookByBookIdWCH(bid);
		//���Ҳ���bookId����鲻�����ݿ�
		if(tmp == null){
			return null;
		}
		res.setBookid(tmp.getId());
		res.setName(tmp.getName());
		res.setQuantity(0);
		
		return res;
	}
	public OrderItemVDao(){
	}
	public BookDao getBd() {
		return bd;
	}
	public void setBd(BookDao bd) {
		this.bd = bd;
	} 
}

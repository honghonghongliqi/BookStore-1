package com.ssh.model;
//������ͼ��
public class OrderItemV{
	protected int bookid;
	protected String name;
	protected int quantity;
	//��д�ȽϺ���
	public boolean equals(Object obj) {
        if (obj instanceof OrderItemV) {
        	OrderItemV tmp = (OrderItemV) obj;
            System.out.println("tmp.id:"+ tmp.getBookid());
            if(this.getBookid() == tmp.getBookid())
            	return true;
        }
        return false;
    }
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

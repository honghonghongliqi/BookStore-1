package com.ssh.model;

public class OrderItems {
	private int id;
	private int ordId;
	private int bookId;
	private int quantity;//��������
	private float subtotal;//С�Ƽ۸�
	//�����¶�������Ҫ�Ļ�������
	public void setAll(int bookId,int quantity,float subtotal){
		this.bookId = bookId;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}

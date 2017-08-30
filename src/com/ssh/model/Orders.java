package com.ssh.model;

public class Orders {
	private int id;
	private int cusId;
	private int addressId;
	private int payType;//���ʽ	0:��������,1:����֧��
	private float price;//�����ܽ��
	private java.sql.Timestamp createDate;
	private int orderStatus;//����״̬	1:���µ���2:�ѷ�����3:�������  -1������ȡ������ -2���ѷ�������ȡ������ -3��ȡ������
	//������������
	public void setAll(int cusId, int addressId,int payType,float price
			,java.sql.Timestamp createDate,int orderStatus){
		this.cusId = cusId;
		this.addressId = addressId;
		this.payType = payType;
		this.price = price;
		this.createDate = createDate;
		this.orderStatus = orderStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}

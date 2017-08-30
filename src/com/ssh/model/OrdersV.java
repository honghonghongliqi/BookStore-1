package com.ssh.model;

import java.util.ArrayList;
import java.util.List;

public class OrdersV {
	private int id;
	private int cusId;
	private ConsignmentAddress address;
	private String payType;//���ʽ	0:��������,1:����֧��
	private float price;//�����ܽ��
	private String createDate;
	private String orderStatus;//����״̬	1:���µ���2:�ѷ�����3:�������  -1������ȡ������ -2���ѷ�������ȡ������ -3��ȡ������
	private List itemList = new ArrayList();
	public void setAll(Orders o,ConsignmentAddress address, List<OrderItemsV> l){
		this.id = o.getId();
		this.cusId = o.getCusId();
		this.address = address;
		if(o.getPayType() == 0)
			this.payType = "��������";
		this.price = o.getPrice();
		this.createDate = o.getCreateDate().toString();
		System.out.print("OrderStatus="); 
		switch(o.getOrderStatus()) 
		{ 
		case 1: 
			this.orderStatus = "���µ�";
		break; 
		case 2: 
			this.orderStatus = "�ѷ���";
		break;
		case 3: 
			this.orderStatus = "�������";
		break;
		case -1: 
			this.orderStatus = "����ȡ������";
		break;
		case -2: 
			this.orderStatus = "�ѷ�������ȡ������";
		break;
		case -3: 
			this.orderStatus = "ȡ������";
		break;
		default: 
			System.out.println("default"); 
		break; 
		} 
		this.itemList = l;
	}
	//��д�ȽϺ���
	public boolean equals(Object obj) {
        if (obj instanceof OrdersV) {
        	OrdersV tmp = (OrdersV) obj;
            System.out.println("�Ƚϵ�OrdersV_id��"+ tmp.getId());
            if(this.getId() == tmp.getId())
            	return true;
        }
        return false;
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
	public ConsignmentAddress getAddress() {
		return address;
	}
	public void setAddress(ConsignmentAddress address) {
		this.address = address;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List getItemList() {
		return itemList;
	}
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}
}

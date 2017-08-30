package com.ssh.model;

public class ConsignmentAddress {
	private int id;  //�ջ���ַ���
	private Customer customer;  //�ջ���ַ�����û�
	private String consignmentName;  //�ջ���
	private String address;  //��ϸ��ַ
	private String postCode;  //��������
	private String phone;  //��ϵ�绰
	private int flag;  //�ջ���ַɾ�����
	
	public ConsignmentAddress(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getConsignmentName() {
		return consignmentName;
	}

	public void setConsignmentName(String consignmentName) {
		this.consignmentName = consignmentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}

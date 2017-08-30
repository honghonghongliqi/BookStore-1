package com.ssh.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.ConsignmentAddressDao;
import com.ssh.model.ConsignmentAddress;
import com.ssh.model.Customer;

public class AddConsignmentAddressAction extends ActionSupport{
	private ConsignmentAddressDao cad;
	private ConsignmentAddress consignmentAddress;
	private Customer customer;
	private final int quantityLimit = 5;
	
	public AddConsignmentAddressAction(){}
	
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		customer = (Customer)session.get("theCustomer");
		if(customer == null){
			session.put("info", "���ʧ�ܣ���¼��Ϣ�ѹ��ڣ���ˢ��ҳ�沢���ԣ�");
			return "input";
		}else if(cad.checkAboveTheLimit(customer.getId(), quantityLimit)){
			session.put("info", "���ʧ�ܣ��ջ���ַ�����Ѵ����ޣ�");
			return "input";
		}else{
			consignmentAddress.setCustomer(customer);
			if(cad.addConsignmentAddress(consignmentAddress)){
				session.put("info", "��ӳɹ�!");
				return "success";
			}else{
				session.put("info", "���ʧ�ܣ�");
				return "input";
			}
		}
	}
	
	public void validate(){
		if(consignmentAddress.getConsignmentName() == null || consignmentAddress.getConsignmentName().equals("")){
			addFieldError("consignmentAddress.consignmentName", "�ջ��˲���Ϊ��");
		}else if(consignmentAddress.getConsignmentName().length() > 20){
			addFieldError("consignmentAddress.consignmentName", "�ջ��˳��Ȳ��ܳ���20λ");
		}
		
		if(consignmentAddress.getAddress() == null || consignmentAddress.getAddress().equals("")){
			addFieldError("consignmentAddress.address", "��ϸ��ַ����Ϊ��");
		}else if(consignmentAddress.getAddress().length() > 100){
			addFieldError("consignmentAddress.address", "��ַ���Ȳ��ܴ���100λ");
		}
		
		if(consignmentAddress.getPostCode() == null || consignmentAddress.getPostCode().equals("")){
			addFieldError("consignmentAddress.postCode", "�������벻��Ϊ��");
		}else if(checkNumberOnly(consignmentAddress.getPostCode()) == false){
			addFieldError("consignmentAddress.postCode", "�ʱ಻�ܰ�����������������ַ�");
		}else if(consignmentAddress.getPostCode().length() != 6){
			addFieldError("consignmentAddress.postCode", "�ʱ೤�ȱ���Ϊ6λ");
		}
		
		if(consignmentAddress.getPhone() == null || consignmentAddress.getPhone().equals("")){
			addFieldError("consignmentAddress.phone", "��ϵ��ʽ����Ϊ��");
		}else if(checkNumberOnly(consignmentAddress.getPhone()) == false){
			addFieldError("consignmentAddress.phone", "��ϵ�绰���ܰ�������������ַ�");
		}else if(consignmentAddress.getPhone().length() > 11){
			addFieldError("consignmentAddress.phone", "��ϵ�绰���Ȳ��ܳ���11λ");
		}
	}
	
	public boolean checkNumberOnly(String str){
		for(int i = 0; i < str.length(); i++){
			if(!(str.charAt(i) >= 48 && str.charAt(i) <= 57)){
				return false;
			}
		}
		return true;
	}
	
	public ConsignmentAddressDao getCad() {
		return cad;
	}

	public void setCad(ConsignmentAddressDao cad) {
		this.cad = cad;
	}

	public ConsignmentAddress getConsignmentAddress() {
		return consignmentAddress;
	}

	public void setConsignmentAddress(ConsignmentAddress consignmentAddress) {
		this.consignmentAddress = consignmentAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

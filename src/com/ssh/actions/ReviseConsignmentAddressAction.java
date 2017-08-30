package com.ssh.actions;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.ConsignmentAddressDao;
import com.ssh.model.ConsignmentAddress;
import com.ssh.model.Customer;

public class ReviseConsignmentAddressAction extends ActionSupport{
	private ConsignmentAddress consignmentAddress;
	private ConsignmentAddressDao cad;
	
	public ReviseConsignmentAddressAction(){}
	
	public String execute(){
		consignmentAddress.setCustomer((Customer)ActionContext.getContext().getSession().get("theCustomer"));
		if(cad.reviseConsignmentAddress(consignmentAddress)){
			ActionContext.getContext().getSession().put("theConsignmentAddress", consignmentAddress);
			ActionContext.getContext().getSession().put("info", "�޸ĳɹ�");
			return "success";
		}else{
			ActionContext.getContext().getSession().put("info", "�޸�ʧ��");
			return "input";
		}
	}
	
	public void validate(){
		if(consignmentAddress.getConsignmentName() == null || consignmentAddress.getConsignmentName().equals("")){
			addFieldError("consignmentAddress.consignmentName", "�ջ��˲���Ϊ��");
		}else if(consignmentAddress.getConsignmentName().length() > 20){
			addFieldError("consignmentAddress.consignmentName", "�ջ��˳��Ȳ��ܳ���20λ");
		}
		
		if(consignmentAddress.getAddress() == null || consignmentAddress.getAddress().equals("")){
			addFieldError("consignmentAddress.address", "��ַ����Ϊ��");
		}else if(consignmentAddress.getAddress().length() > 100){
			addFieldError("consignmentAddress.address", "��ַ���Ȳ��ܴ���100λ");
		}
		
		if(consignmentAddress.getPostCode() == null || consignmentAddress.getPostCode().equals("")){
			addFieldError("consignmentAddress.postCode", "�ʱ಻��Ϊ��");
		}else if(checkNumberOnly(consignmentAddress.getPostCode()) == false){
			addFieldError("consignmentAddress.postCode", "�ʱ಻�ܰ�����������������ַ�");
		}else if(consignmentAddress.getPostCode().length() != 6){
			addFieldError("consignmentAddress.postCode", "�ʱ೤�ȱ���Ϊ6λ");
		}
		
		if(consignmentAddress.getPhone() == null || consignmentAddress.getPhone().equals("")){
			addFieldError("consignmentAddress.phone", "��ϵ�绰����Ϊ��");
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

	public ConsignmentAddress getConsignmentAddress() {
		return consignmentAddress;
	}

	public void setConsignmentAddress(ConsignmentAddress consignmentAddress) {
		this.consignmentAddress = consignmentAddress;
	}

	public ConsignmentAddressDao getCad() {
		return cad;
	}

	public void setCad(ConsignmentAddressDao cad) {
		this.cad = cad;
	}
}

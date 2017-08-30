package com.ssh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.CustomerDao;
import com.ssh.model.Customer;

public class ReviseDataAction extends ActionSupport{
	private Customer customer;
	private CustomerDao cd;
	
	public ReviseDataAction(){}

	public String execute(){
		if(cd.reviseCustomerData(customer) == true){
			ActionContext.getContext().getSession().put("theCustomer", customer);
			addFieldError("customer.email", "�޸����ϳɹ�");
			return "success";
		}else{
			addFieldError("customer.email", "�޸�����ʧ��");
			return "input";
		}
	}
	
	public void validate(){
		if(customer.getEmail() == null || customer.getEmail().equals("")){
			addFieldError("customer.email", "���䲻��Ϊ��");
		}else if(checkEmail(customer.getEmail()) == false){
			addFieldError("customer.email", "�����ʽ���󣬱������.��@");
		}else if(customer.getEmail().length() > 30){
			addFieldError("customer.email", "���䳤�Ȳ���2~30λ����Ч��Χ��");
		}
		
		if(customer.getMobile() == null || customer.getMobile().equals("")){
			addFieldError("customer.mobile", "�ֻ�����Ϊ��");
		}else if(checkMobile(customer.getMobile()) == false){
			addFieldError("customer.mobile", "�ֻ����ܰ�������������������ַ�");
		}else if(customer.getMobile().length() != 11){
			addFieldError("customer.mobile", "�ֻ����ȱ���Ϊ11λ");
		}
	}
	
	public boolean checkEmail(String str){
		int dot = 0, at = 0;
		
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '.'){
				dot = 1;
			}
			if(str.charAt(i) == '@'){
				at = 1;
			}
		}
		
		if(dot == 1 && at == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkMobile(String str){
		for(int i = 0; i < str.length(); i++){
			if(!(str.charAt(i) >= 48 && str.charAt(i) <= 57)){
				return false;
			}
		}
		return true;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerDao getCd() {
		return cd;
	}

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
}

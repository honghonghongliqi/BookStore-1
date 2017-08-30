package com.ssh.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.CustomerDao;
import com.ssh.model.Customer;

public class LoginAction extends ActionSupport{
	private Customer customer;
	private CustomerDao cd;
	
	public LoginAction(){}
	
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		customer = cd.checkCustomerExist(customer.getRegName(), customer.getPassword());
		
		//wch
		ActionContext.getContext().getSession().put("customerId", customer.getId());
		ActionContext.getContext().getSession().put("shopping",null);
		//
		
		session.put("theCustomer",customer);
		return "success";
	}

	public void validate(){
		int flag = 1;
		if(customer.getRegName() == null || customer.getRegName().equals("")){
			flag = 0;
			addFieldError("customer.regName", "�û�������Ϊ��");
		}else if(customer.getRegName().length() < 1 || customer.getRegName().length() > 12){
			flag = 0;
			addFieldError("customer.regName", "�û������Ȳ���1~12λ����Ч��Χ��");
		}else if(checkFormat(customer.getRegName()) == false){
			flag = 0;
			addFieldError("customer.regName", "�û������ܰ�������ĸ��������������ַ�");
		}
		
		if(customer.getPassword() == null || customer.getPassword().equals("")){
			flag = 0;
			addFieldError("customer.password", "���벻��Ϊ��");
		}else if(customer.getPassword().length() < 6 || customer.getPassword().length() > 10){
			flag = 0;
			addFieldError("customer.password", "���볤�Ȳ���6~10λ����Ч��Χ��");
		}
		
		if(flag == 1 && cd.checkCustomerExist(customer.getRegName(), customer.getPassword()) == null){
			addFieldError("customer.regName", "�û������ڻ��������");
		}
	}
	
	public boolean checkFormat(String str){
		for(int i = 0; i < str.length(); i++){
			if(!((str.charAt(i) >= 48 && str.charAt(i) <= 57)
					|| (str.charAt(i) >= 65 && str.charAt(i) <= 90)
					|| (str.charAt(i) >= 97 && str.charAt(i) <= 122))){
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

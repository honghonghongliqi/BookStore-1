package com.ssh.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.CustomerDao;
import com.ssh.model.Customer;

public class RegisterAction extends ActionSupport{
	private Customer customer;
	private String passwordConfirm;
	private CustomerDao cd;
	
	public RegisterAction(){}

	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(cd.registerCustomer(customer) == true){
			customer = cd.checkCustomerExist(customer.getRegName(), customer.getPassword());
			session.put("theCustomer", customer);
			
			//wch
			ActionContext.getContext().getSession().put("customerId", customer.getId());
			ActionContext.getContext().getSession().put("shopping",null);
			//
			return "success";
		}else{
			addFieldError("customer.regName", "ע��ʧ��");
			return "input";
		}
	}
	
	public void validate(){
		if(customer.getRegName() == null || customer.getRegName().equals("")){
			addFieldError("customer.regName", "�û�������Ϊ��");
		}else if(customer.getRegName().length() < 1 || customer.getRegName().length() > 12){
			addFieldError("customer.regName", "�û������Ȳ���1~12λ����Ч��Χ��");
		}else if(checkRegName(customer.getRegName()) == false){
			addFieldError("customer.regName", "�û������ܰ�������ĸ��������������ַ�");
		}else if(cd.checkRegNameUnused(customer.getRegName()) == false){
			addFieldError("customer.regName", "�û����ѱ�ʹ��");
		}
		
		if(customer.getPassword() == null || customer.getPassword().equals("")){
			addFieldError("customer.password", "���벻��Ϊ��");
		}else if(customer.getPassword().length() < 6 || customer.getPassword().length() > 10){
			addFieldError("customer.password", "���볤�Ȳ���6~10λ����Ч��Χ��");
		}
		
		if(passwordConfirm == null || passwordConfirm.equals("")){
	    	addFieldError("passwordConfirm", "ȷ�����벻��Ϊ��");
	    }else if(customer.getPassword() == null || !passwordConfirm.equals(customer.getPassword())){
	    	addFieldError("passwordConfirm", "ǰ�����벻һ��");
	    }
		
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
	
	public boolean checkRegName(String str){
		for(int i = 0; i < str.length(); i++){
			if(!((str.charAt(i) >= 48 && str.charAt(i) <= 57)
					|| (str.charAt(i) >= 65 && str.charAt(i) <= 90)
					|| (str.charAt(i) >= 97 && str.charAt(i) <= 122))){
				return false;
			}
		}
		return true;
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public CustomerDao getCd() {
		return cd;
	}

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
}

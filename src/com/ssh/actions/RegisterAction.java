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
			addFieldError("customer.regName", "注册失败");
			return "input";
		}
	}
	
	public void validate(){
		if(customer.getRegName() == null || customer.getRegName().equals("")){
			addFieldError("customer.regName", "用户名不能为空");
		}else if(customer.getRegName().length() < 1 || customer.getRegName().length() > 12){
			addFieldError("customer.regName", "用户名长度不在1~12位的有效范围内");
		}else if(checkRegName(customer.getRegName()) == false){
			addFieldError("customer.regName", "用户名不能包含除字母数字以外的其他字符");
		}else if(cd.checkRegNameUnused(customer.getRegName()) == false){
			addFieldError("customer.regName", "用户名已被使用");
		}
		
		if(customer.getPassword() == null || customer.getPassword().equals("")){
			addFieldError("customer.password", "密码不能为空");
		}else if(customer.getPassword().length() < 6 || customer.getPassword().length() > 10){
			addFieldError("customer.password", "密码长度不在6~10位的有效范围内");
		}
		
		if(passwordConfirm == null || passwordConfirm.equals("")){
	    	addFieldError("passwordConfirm", "确认密码不能为空");
	    }else if(customer.getPassword() == null || !passwordConfirm.equals(customer.getPassword())){
	    	addFieldError("passwordConfirm", "前后密码不一致");
	    }
		
		if(customer.getEmail() == null || customer.getEmail().equals("")){
			addFieldError("customer.email", "邮箱不能为空");
		}else if(checkEmail(customer.getEmail()) == false){
			addFieldError("customer.email", "邮箱格式错误，必须包含.和@");
		}else if(customer.getEmail().length() > 30){
			addFieldError("customer.email", "邮箱长度不在2~30位的有效范围内");
		}
		
		if(customer.getMobile() == null || customer.getMobile().equals("")){
			addFieldError("customer.mobile", "手机不能为空");
		}else if(checkMobile(customer.getMobile()) == false){
			addFieldError("customer.mobile", "手机不能包含除数字以外的其他字符");
		}else if(customer.getMobile().length() != 11){
			addFieldError("customer.mobile", "手机长度必须为11位");
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

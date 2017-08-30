package com.ssh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.CustomerDao;
import com.ssh.model.Customer;

public class RevisePasswordAction extends ActionSupport{
	private Customer customer;
	private CustomerDao cd;
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm; 
	
	public RevisePasswordAction(){}

	public String execute(){
		customer = (Customer)ActionContext.getContext().getSession().get("theCustomer");
		customer.setPassword(newPassword);
		if(cd.reviseCustomerPassword(customer) == true){
			ActionContext.getContext().getSession().put("theCustomer", customer);
			addFieldError("oldPassword", "�޸�����ɹ�");
			return "success";
		}else{
			addFieldError("oldPassword", "�޸�����ʧ��");
			return "input";
		}
	}
	
	public void validate(){
		customer = (Customer)ActionContext.getContext().getSession().get("theCustomer");
		if(customer == null){
			addFieldError("oldPassword", "��¼�ѹ��ڣ������µ�¼");
		}
		if(oldPassword == null || oldPassword.equals("")){
			addFieldError("oldPassword", "�����벻��Ϊ��");
		}else if(!oldPassword.equals(customer.getPassword())){
			addFieldError("oldPassword", "�����벻��ȷ");
		}
		
		if(newPassword == null || newPassword.equals("")){
			addFieldError("newPassword", "�����벻��Ϊ��");
		}else if(newPassword.length() < 6 || newPassword.length() > 10){
			addFieldError("newPassword", "�����볤�Ȳ���6~10λ����Ч��Χ��");
		}
		
		if(newPasswordConfirm == null || newPasswordConfirm.equals("")){
	    	addFieldError("newPasswordConfirm", "ȷ�����벻��Ϊ��");
	    }else if(newPassword == null || !newPasswordConfirm.equals(newPassword)){
	    	addFieldError("newPasswordConfirm", "ǰ�����벻һ��");
	    }
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
}

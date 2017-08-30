package com.ssh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.dao.CustomerDao;

public class JumpToDeleteCustomerAction {
	private int cusId;
	private CustomerDao cd;
	
	public JumpToDeleteCustomerAction(){}

	public String execute(){
		if(cd.deleteCustomer(cd.getCustomerById(cusId))){
			ActionContext.getContext().getSession().put("info", "ɾ���ɹ�");
			return "success";
		}else{
			ActionContext.getContext().getSession().put("info", "ɾ��ʧ��");
			return "input";
		}
	}
	
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public CustomerDao getCd() {
		return cd;
	}

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
	
	
}

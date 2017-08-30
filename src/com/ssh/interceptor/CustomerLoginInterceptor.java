package com.ssh.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ssh.model.Customer;

public class CustomerLoginInterceptor extends AbstractInterceptor{
	@Override //��д�����intercept����
	public String intercept(ActionInvocation ai) throws Exception {
		Customer cus = (Customer)ActionContext.getContext().getSession().get("theCustomer");
		if(cus != null){ //�Ѿ��ɹ���¼

			return ai.invoke();
		}else{//��û��¼
			
			return "unlogin";
		}
	}
}

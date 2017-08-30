package com.ssh.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ssh.model.Administrator;
import com.ssh.model.Customer;

public class AdminLoginInterceptor extends AbstractInterceptor {
	@Override //��д�����intercept����
	public String intercept(ActionInvocation ai) throws Exception {
		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("theAdministrator");
		if(admin != null){ //�Ѿ��ɹ���¼

			return ai.invoke();
		}else{//��û��¼
			
			return "unlogin";
		}
	}
}

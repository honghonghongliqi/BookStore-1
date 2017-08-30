package com.ssh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.dao.ConsignmentAddressDao;
import com.ssh.model.ConsignmentAddress;

public class JumpToDeleteConsignmentAddressAction {
	private String locationHref;
	private int consignmentAddressId;
	private ConsignmentAddressDao cad;
	
	public JumpToDeleteConsignmentAddressAction(){}
	
	public String execute(){
		ActionContext.getContext().getSession().put("preLocation", locationHref);
		
		ConsignmentAddress ca = cad.getConsignmentAddressById(consignmentAddressId);
		if(ca == null){
			ActionContext.getContext().getSession().put("info", "��¼�ѹ���,������");
			return "input";
		}else if(cad.deleteConsignmentAddress(ca)){
			ActionContext.getContext().getSession().put("info", "ɾ���ɹ�");
			return "success";
		}else{
			ActionContext.getContext().getSession().put("info", "ɾ��ʧ��");
			return "input";
		}
	}

	public String getLocationHref() {
		return locationHref;
	}

	public void setLocationHref(String locationHref) {
		this.locationHref = locationHref;
	}

	public int getConsignmentAddressId() {
		return consignmentAddressId;
	}

	public void setConsignmentAddressId(int consignmentAddressId) {
		this.consignmentAddressId = consignmentAddressId;
	}

	public ConsignmentAddressDao getCad() {
		return cad;
	}

	public void setCad(ConsignmentAddressDao cad) {
		this.cad = cad;
	}
}

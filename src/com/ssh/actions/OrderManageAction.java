package com.ssh.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.OrderDao;
import com.ssh.model.Orders;
import com.ssh.model.OrdersV;

public class OrderManageAction extends ActionSupport {
	private List<OrdersV> list = new ArrayList();
	private OrderDao orderDao;
	protected int id; //������ʾ���ݵ�����
	protected final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	protected int pageNo=1; //������,�ӵ�1ҳ��ʼ
	protected int currentPage; //��ǰҳ
	protected int totalPage; //��ҳ��
	protected List<OrdersV> ordersVListInPage;
	
	public String execute(){
		list = orderDao.queryOrder();
		System.out.println("list"+list);
		
		//��ҳ����
		if(list == null){
			return SUCCESS;
		}else if(list.size()%pageSize==0){
			totalPage=list.size()/pageSize;
		}else{
			totalPage=list.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ������
		List tmp;
		if(list.size() == 0){
			ordersVListInPage = null;
			return SUCCESS;
		}else if(list.size() < pageNo*pageSize){
			tmp= list.subList((pageNo-1)*pageSize,list.size());
		}else{
			tmp= list.subList((pageNo-1)*pageSize,pageNo*pageSize);
		}
		ordersVListInPage = tmp;
		
		currentPage=pageNo;
		
		if(list == null)
			return INPUT;
		else
			return SUCCESS;
	}

	public List<OrdersV> getList() {
		return list;
	}

	public void setList(List<OrdersV> list) {
		this.list = list;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<OrdersV> getOrdersVListInPage() {
		return ordersVListInPage;
	}

	public void setOrdersVListInPage(List<OrdersV> ordersVListInPage) {
		this.ordersVListInPage = ordersVListInPage;
	}

	public int getPageSize() {
		return pageSize;
	}
	
}

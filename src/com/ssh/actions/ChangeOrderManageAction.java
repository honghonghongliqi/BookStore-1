package com.ssh.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.OrderDao;
import com.ssh.model.Orders;
import com.ssh.model.OrdersV;

public class ChangeOrderManageAction extends ActionSupport{
	private List<OrdersV> list = new ArrayList();
	private OrderDao orderDao;
	protected int operateNum = 0;//������ 0��Ĭ�� 1������  2��ͬ��ȡ�� 3����ͬ��ȡ������ԭ״̬ 
	protected int operateId = 0;//��ɶ�����id
	protected int id; //������ʾ���ݵ�����
	protected final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	protected int pageNo=1; //������,�ӵ�1ҳ��ʼ
	protected int currentPage; //��ǰҳ
	protected int totalPage; //��ҳ��
	protected List<OrdersV> ordersVListInPage;
	
	public String execute(){
		list = orderDao.queryOrder();
		System.out.println("list"+list);
		System.out.println("������"+operateNum+"����ID"+operateId);
		//����
		out:if(operateNum == 1 && operateId != 0){
			//ֻ����״̬{���µ�} ���������ݿ���û�ж����޸�״̬
			Orders orders = orderDao.queryOrderById(operateId);
			if(orders == null || orders.getOrderStatus() != 1 ){
				operateNum = 0;// *����idΪ0����Ȼ�����޽�������
				operateId = 0;
				break out;
			}
			orderDao.updateStatusById(operateId, 2);
			list = orderDao.queryOrder();
			operateNum = 0;
			operateId = 0;
		}
		//ͬ��ȡ������
		outA:if(operateNum == 2 && operateId != 0){
			//ֻ����״̬{ȡ������������ȡ������} ���������ݿ���û�ж����޸�״̬
			Orders orders = orderDao.queryOrderById(operateId);
			if(orders == null || (orders.getOrderStatus() != -1 &&  orders.getOrderStatus() != -2)){
				operateNum = 0;// *����idΪ0����Ȼ�����޽�������
				operateId = 0;
				break outA;
			}
			orderDao.updateStatusById(operateId, -3);
			list = orderDao.queryOrder();
			operateNum = 0;
			operateId = 0;
		}
		//3����ͬ��ȡ������ԭ״̬ 
		outB:if(operateNum == 3 && operateId != 0){
			//ֻ����״̬{ȡ������������ȡ������} ���������ݿ���û�ж����޸�״̬
			Orders orders = orderDao.queryOrderById(operateId);
			if(orders == null || (orders.getOrderStatus() != -1 &&  orders.getOrderStatus() != -2)){
				operateNum = 0;// *����idΪ0����Ȼ�����޽�������
				operateId = 0;
				break outB;
			}
			System.out.println("�ڴ������"+orders.getOrderStatus()*-1);
			orderDao.updateStatusById(operateId, orders.getOrderStatus()*-1);
			list = orderDao.queryOrder();
			operateNum = 0;
			operateId = 0;
		}
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

	public int getOperateNum() {
		return operateNum;
	}

	public void setOperateNum(int operateNum) {
		this.operateNum = operateNum;
	}

	public int getOperateId() {
		return operateId;
	}

	public void setOperateId(int operateId) {
		this.operateId = operateId;
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

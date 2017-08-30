package com.ssh.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.OrderDao;
import com.ssh.model.Orders;
import com.ssh.model.OrdersV;

public class ChangeOrderList extends ActionSupport{
	private int cid;
	private List<OrdersV> list = new ArrayList();
	private OrderDao orderDao;
	protected int undoId = 0;//����ȡ��������id
	protected int doneId = 0;//��ɶ�����id
	protected int id; //������ʾ���ݵ�����
	protected final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	protected int pageNo=1; //������,�ӵ�1ҳ��ʼ
	protected int currentPage; //��ǰҳ
	protected int totalPage; //��ҳ��
	protected List<OrdersV> ordersVListInPage;
	
	public String execute(){
		Map session=ActionContext.getContext().getSession();
		Object cidObj = (Object)session.get("customerId");
		if(cidObj == null){//������null����
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return INPUT;
		}
		cid = Integer.valueOf(cidObj.toString());
		if(cid == 0){ //�û�û��¼--��̨��֤
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!@##$");
			return INPUT;
		}
		list = orderDao.queryOrderByCId(cid);
		//����ȡ������
		System.out.println("Ҫȡ���Ķ���ID="+undoId);
		out:if(undoId != 0){
			//ֻ����״̬{���µ����ѷ���ȡ������} ���������ݿ���û�ж����޸�״̬
			Orders orders = orderDao.queryOrderById(undoId);
			if(orders == null || (orders.getOrderStatus() != 1 &&  orders.getOrderStatus() != 2)){
				undoId=0;// *����idΪ0����Ȼ�����޽�������
				break out;
			}
			if(orders.getOrderStatus() == 1)
				orderDao.updateStatusById(undoId, -1);
			if(orders.getOrderStatus() == 2)
				orderDao.updateStatusById(undoId, -2);
			list = orderDao.queryOrderByCId(cid);
			undoId = 0;
		}
		//��ɶ���
		System.out.println("Ҫ��ɵĶ���ID="+doneId);
		outDone:if(doneId != 0){
			//ֻ����״̬{���µ����ѷ���ȡ������}���������ݿ���û�ж����޸�״̬
			Orders orders = orderDao.queryOrderById(doneId);
			if(orders == null || (orders.getOrderStatus() != 1 &&  orders.getOrderStatus() != 2)){
				doneId = 0;// *����idΪ0����Ȼ�����޽�������
				break outDone;
			}
			orderDao.updateStatusById(doneId, 3);
			list = orderDao.queryOrderByCId(cid);
			doneId = 0;
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
		
			return SUCCESS;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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

	public int getUndoId() {
		return undoId;
	}

	public void setUndoId(int undoId) {
		this.undoId = undoId;
	}

	public int getDoneId() {
		return doneId;
	}

	public void setDoneId(int doneId) {
		this.doneId = doneId;
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

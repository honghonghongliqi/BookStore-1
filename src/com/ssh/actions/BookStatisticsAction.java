package com.ssh.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.OrderDao;
import com.ssh.dao.OrderItemsDao;
import com.ssh.model.OrderItemsV;
import com.ssh.model.Orders;
import com.ssh.model.OrdersV;

public class BookStatisticsAction extends ActionSupport{
	private List<OrderItemsV> list = new ArrayList();
	private List<OrderItemsV> orderItemsVListInPage;
	private OrderItemsDao orderItemsDao;
	private int oper = 1;
	private String sdate = new String();
	private String edate = new String();
	private String err;
	private int id; //������ʾ���ݵ�����
	private final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	private int pageNo=1; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage; //��ҳ��

	
	public String execute(){
		if((sdate == null || sdate.isEmpty()) && (edate == null || edate.isEmpty())){
			list = orderItemsDao.getOrderItemsDaoAllStatistics(new String("1753-01-01"), new String("2200-01-01"),oper);
		}else if(sdate == null || sdate.isEmpty()){
			list = orderItemsDao.getOrderItemsDaoAllStatistics(new String("1753-01-01"), edate,oper);
		}else if(edate == null || edate.isEmpty()){
			list = orderItemsDao.getOrderItemsDaoAllStatistics(sdate, new String("2200-01-01"), oper);
		}else{
			list = orderItemsDao.getOrderItemsDaoAllStatistics(sdate, edate, oper);
		}
		if(list == null){
			err = ",���Ϊ�գ����������Ƿ���ȷ";
			orderItemsVListInPage= null;
			return SUCCESS;
		}else{
			err = null;
		}
		//��ҳ����{
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
			orderItemsVListInPage = null;
			return SUCCESS;
		}else if(list.size() < pageNo*pageSize){
			tmp= list.subList((pageNo-1)*pageSize,list.size());
		}else{
			tmp= list.subList((pageNo-1)*pageSize,pageNo*pageSize);
		}
		orderItemsVListInPage = tmp;
		for(Iterator it = orderItemsVListInPage.iterator(); it.hasNext();){
			OrderItemsV orderItemsV = (OrderItemsV)it.next();
			System.out.println("getBookName"+orderItemsV.getBookName());
		}
		currentPage=pageNo;
		
		if(list == null)
			return INPUT;
		else
			return SUCCESS;
	}


	public List<OrderItemsV> getList() {
		return list;
	}


	public void setList(List<OrderItemsV> list) {
		this.list = list;
	}


	public List<OrderItemsV> getOrderItemsVListInPage() {
		return orderItemsVListInPage;
	}


	public void setOrderItemsVListInPage(List<OrderItemsV> orderItemsVListInPage) {
		this.orderItemsVListInPage = orderItemsVListInPage;
	}


	public OrderItemsDao getOrderItemsDao() {
		return orderItemsDao;
	}


	public void setOrderItemsDao(OrderItemsDao orderItemsDao) {
		this.orderItemsDao = orderItemsDao;
	}


	public int getOper() {
		return oper;
	}


	public void setOper(int oper) {
		this.oper = oper;
	}


	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {
		this.sdate = sdate;
	}


	public String getEdate() {
		return edate;
	}


	public void setEdate(String edate) {
		System.out.println("edate"+edate);
		this.edate = edate;
	}


	public String getErr() {
		return err;
	}


	public void setErr(String err) {
		this.err = err;
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


	public int getPageSize() {
		return pageSize;
	}
}

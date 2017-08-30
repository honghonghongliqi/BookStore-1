package com.ssh.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ssh.model.Book;
import com.ssh.model.ConsignmentAddress;
import com.ssh.model.OrderItemV;
import com.ssh.model.OrderItems;
import com.ssh.model.Orders;
import com.ssh.model.OrdersV;

public class OrderDao {
	private BookDao bookDao;
	private OrderItemsDao orderItemsDao;
	private ConsignmentAddressDao consignmentAddressDao;
	private SessionFactory sessionFactory;
	//������ɹ�-���������У���ͼ�ã���list ʧ�� NULL
	public List<OrdersV> queryOrder(){
		Session session=null;
		List<OrdersV> list = new ArrayList();
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from Orders ORDER BY id DESC";
			Query query=session.createQuery(queryString);
			List ordersList= query.list();
			if(ordersList == null || ordersList.size() == 0){
				System.out.println("queryOrder��ѯ���Ϊ��");
				return null;
			}
			for(Iterator it = ordersList.iterator(); it.hasNext();){
				Orders orders = (Orders)it.next();
				ConsignmentAddress  consignmentAddress = 
		consignmentAddressDao.getConsignmentAddressById(orders.getAddressId());
				List orderItemsVList = 
		orderItemsDao.getOrderItemsDaoByOrdId(orders.getId());
				OrdersV ordersV = new OrdersV();
				ordersV.setAll(orders, consignmentAddress, orderItemsVList);
				list.add(ordersV);
			}
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	//��������ѯ���� ,����������Id,������ɹ����ض�������ʧ�ܷ���null;
	public Orders queryOrderById(int id){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from Orders where Id=?";
			Query query=session.createQuery(queryString);
			query.setParameter(0, id);
			List list = query.list();
			if(list == null || list.size() == 0){
				System.out.println("queryOrderById��ѯ�����Ϊ��,idΪ"+ id);
				return null;
			}
			return (Orders)list.get(0);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	//���������¶������ж���״̬,���� ������id ���������� ,���ؽ�� �ɹ���1 ,ʧ�ܣ�0
	public int updateStatusById(int oId,int status){
		Session session=null;
		List<OrdersV> list = new ArrayList();
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			
			Transaction	trans=session.beginTransaction();
			String sql = "update Orders t set t.orderStatus='"
				+ status
				+ "' where id="
				+ oId;
			System.out.println("updateStatusById_sql:"+ sql);
	        Query query = session.createQuery(sql);  
	        query.executeUpdate();  
	        trans.commit(); 
			
			return 1;
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}finally{
			//�ر�session
			session.close();
		}
	}
	//��������û����ж���  ���룺 �û�id 
	public List<OrdersV> queryOrderByCId (int cid){
		Session session=null;
		List<OrdersV> list = new ArrayList();
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from Orders where cusId=? ORDER BY id DESC";
			Query query=session.createQuery(queryString);
			query.setParameter(0, cid);
			List ordersList= query.list();
			for(Iterator it = ordersList.iterator(); it.hasNext();){
				Orders orders = (Orders)it.next();
				ConsignmentAddress  consignmentAddress = 
		consignmentAddressDao.getConsignmentAddressById(orders.getAddressId());
				List orderItemsVList = 
		orderItemsDao.getOrderItemsDaoByOrdId(orders.getId());
				OrdersV ordersV = new OrdersV();
				ordersV.setAll(orders, consignmentAddress, orderItemsVList);
				list.add(ordersV);
			}
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	//submitOrder�����Ĳ������
	public void output(String str,List<OrderItems> list){
		System.out.println("---------------"+str+"------------------");
		if(list == null || list.size()==0)return ;
		System.out.println("size:" + list.size());
		for(Iterator it = list.iterator(); it.hasNext();){
			OrderItems tmp = (OrderItems)it.next();
			System.out.println(tmp.getBookId());
			System.out.println(tmp.getQuantity());
			System.out.println(tmp.getSubtotal());
			System.out.println("-------");
		}
		System.out.println("---------------------------");
	}
	//������쳣��-1  �ɹ���0  ��治��򲻴����Ȿ�飺BOOKID ���룺���ﳵlist
	public List<String> checkstockStatus(List<OrderItemV> shopping){
		List list = new ArrayList();
		try{
			//�����б����list return ��������治�� �������治����ID
			for(Iterator it = shopping.iterator(); it.hasNext();){
				OrderItemV tmp = (OrderItemV)it.next();
				Book book = bookDao.getBookByBookIdWCH(tmp.getBookid());
				if(book== null){
					list.add(tmp.getName()+"ͼ�鲻����");
				}
				if(book.getStockStatus() < tmp.getQuantity()){
					list.add(book.getName()+"����������㣬��ǰ�����Ϊ"+book.getStockStatus());
				}
			}
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	public int submitOrder(List<OrderItemV> shopping, int cusId,
			int addressId){
		Session session=null;
		
		List<OrderItems> list = new ArrayList();//����ϸ�����
		float subtotal = 0;//С��
		float cnt = 0;//�ܼ�
		Orders orders = new Orders();//��������
		int orderId = 0;
		int num = -1;//���ݲ���󷵻�����
		String sql = null;
		Transaction trans = null;
		List stockStatus = new ArrayList();//������
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			//�����б����list return ��������治�� �������治����ID
			for(Iterator it = shopping.iterator(); it.hasNext();){
				OrderItemV tmp = (OrderItemV)it.next();
				Book book = bookDao.getBookByBookIdWCH(tmp.getBookid());
				stockStatus.add(book);
				//��治��
				if(book.getStockStatus() < tmp.getQuantity()){
					return tmp.getBookid();
				}
				subtotal = book.getPrice()* tmp.getQuantity();
				cnt += subtotal;
				//list������������ã�����ÿ�α���new�¶���
				OrderItems orderItems = new OrderItems();//����ϸ������
				orderItems.setAll(tmp.getBookid(), tmp.getQuantity(), subtotal);
				list.add(orderItems);
			}
			output("add", list);
			//���������µ�
			//��һ������
			trans=session.beginTransaction();
			//�������ݣ���������
			orders.setAll(cusId, addressId, 0, cnt, 
					new Timestamp((new java.util.Date()).getTime()), 1);
			//orders.setAll(cusId, addressId, payType, price, createDate, orderStatus)
			sql =session.save(orders).toString();
			orderId=Integer.parseInt(sql); //�浽��������,��δ����д�����ݿ�
			//�ύ����
			trans.commit();	
			System.out.println("orderId="+orderId);
			//����ʧ��
			if(orderId < 1){
				System.out.println("��������ʧ��");
				return -2;
			}
			//���붩��
			for(Iterator it = list.iterator(); it.hasNext();){
				OrderItems orderItems = (OrderItems)it.next();
				orderItems.setOrdId(orderId);
				trans=session.beginTransaction();
				//�������ݣ�������Ӱ�������
				sql = session.save(orderItems).toString();
				num = Integer.parseInt(sql); //�浽��������,��δ����д�����ݿ�
				System.out.println("num1=" + num + "BookId:" +orderItems.getBookId());
				//�ύ����
				trans.commit();	
				if(num < 0){
					System.out.println("submitOrder_return_ERR");
					return -3;
				}
			}
			//���ٿ��
			for(int i = 0; i < list.size(); i++){
				OrderItems orderItems = (OrderItems)list.get(i);
				Book book = (Book)stockStatus.get(i);
				int newStockStatus = book.getStockStatus() - orderItems.getQuantity();
				trans=session.beginTransaction();
				sql = "update Book t set t.stockStatus='"
						+ newStockStatus
						+ "' where id="
						+ orderItems.getBookId();
				System.out.println("StockStatus_sql:"+ sql);
		        Query query = session.createQuery(sql);  
		        query.executeUpdate();  
		        trans.commit(); 
			}
			return 0;
		}catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}finally{
			//�ر�session
			session.close();
		}
	}
/*	//��ѯʡ��id����ѯ�����Ͳ���ʡ�ݲ�����ʡ��id--f=forceǿ��
	public int getProvinceByNameF(String province){
		Session session=null;
		try{
			//���session
			int index = -1;
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from Province where name=?";
			Query query=session.createQuery(queryString);
			query.setParameter(0, province);
			List list = query.list();
			if(list == null || list.size() == 0){
				Transaction trans = session.beginTransaction();
				Province tmp = new Province();
				tmp.setName(province);
				int num=Integer.parseInt(session.save(tmp).toString()); //�浽��������,��δ����д�����ݿ�
				System.out.println("getProvinceByNameF.num:"+num);
				trans.commit();
				//��ִ��
				list = query.list();
			}
			index = ((Province)list.get(0)).getId();
			
			return index;
		}catch(Exception ex){
			ex.printStackTrace();
			return -2;
		}finally{
			//�ر�session
			session.close();
		}
	}*/
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public OrderItemsDao getOrderItemsDao() {
		return orderItemsDao;
	}
	public void setOrderItemsDao(OrderItemsDao orderItemsDao) {
		this.orderItemsDao = orderItemsDao;
	}
	public ConsignmentAddressDao getConsignmentAddressDao() {
		return consignmentAddressDao;
	}
	public void setConsignmentAddressDao(ConsignmentAddressDao consignmentAddressDao) {
		this.consignmentAddressDao = consignmentAddressDao;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

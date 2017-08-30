package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ssh.model.ConsignmentAddress;
import com.ssh.model.Customer;

public class ConsignmentAddressDao {
	SessionFactory sessionFactory;
	
	public ConsignmentAddressDao(){}
	
	/**
	 * ���ݴ�����ջ���ַ��Ų��Ҷ�Ӧ���ջ���ַ 
	 * @param id
	 * @return
	 */
	public ConsignmentAddress getConsignmentAddressById(int id){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from ConsignmentAddress where flag=0 and id = ?";  //���ݿ�Ԥ�����ѯ���
			Query query = session.createQuery(queryString);
			query.setParameter(0, id);  //���ò���
			
			List<ConsignmentAddress> list = (List<ConsignmentAddress>)query.list();
			
			if(list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ���ݴ�����û���Ų�ѯ���ڸ��û���������Ч���ջ���ַ�б�
	 * @param cusId
	 * @return
	 */
	public List<ConsignmentAddress> getAllConsignmentAddressByCusId(int cusId){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from ConsignmentAddress where flag=0 and cusId = ?";  //���ݿ�Ԥ�����ѯ���
			Query query = session.createQuery(queryString);
			query.setParameter(0, cusId);  //���ò���
			
			List<ConsignmentAddress> list = (List<ConsignmentAddress>)query.list();
			
			if(list.size() == 0){
				return null;
			}else{
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ��������޸ĺ���ջ���ַ��Ϣ���������ݿ�
	 * @param consignmentAddress
	 * @return
	 */
	public boolean reviseConsignmentAddress(ConsignmentAddress consignmentAddress){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.update(consignmentAddress);
			trans.commit();
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ɾ��������ջ���ַ
	 * @param consignmentAddress
	 * @return
	 */
	public boolean deleteConsignmentAddress(ConsignmentAddress consignmentAddress){
		Session session = null;
		try{
			consignmentAddress.setFlag(1);  //���ջ���ַ��ɾ���������Ϊ��ɾ��
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.update(consignmentAddress);
			trans.commit();
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ��������ջ���ַ��������ݿ�
	 * @param consignmentAddress
	 * @return
	 */
	public boolean addConsignmentAddress(ConsignmentAddress consignmentAddress){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.save(consignmentAddress);
			trans.commit();
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ������ڴ�����û������������û����ջ���ַ�����Ƿ��Ѿ��ﵽ����
	 * @param cusId
	 * @param quantityLimit
	 * @return
	 */
	public boolean checkAboveTheLimit(int cusId, int quantityLimit){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from ConsignmentAddress where flag=0 and cusId = ?";  //���ݿ�Ԥ�����ѯ���
			Query query = session.createQuery(queryString);
			query.setParameter(0, cusId); //���ò���
			
			List<ConsignmentAddress> list = (List<ConsignmentAddress>)query.list();
			
			if(list.size() >= quantityLimit){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return true;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	
	
	
	
	
	//���룺�û�id ������ɹ�-���û��ĳ��õ�ַlist ʧ��-null
	public List<ConsignmentAddress> getConsignmentAddressbyCusId(int cusId){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from ConsignmentAddress where cusId = ? and  flag = 0";
			Query query=session.createQuery(queryString);
			query.setParameter(0, cusId);
			return query.list();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

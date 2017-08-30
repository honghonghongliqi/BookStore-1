package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import com.ssh.model.Customer;

public class CustomerDao {
	SessionFactory sessionFactory;
	
	public CustomerDao(){}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * ��鴫����û��������������ݿ����Ƿ����
	 * @param regName
	 * @param password
	 * @return
	 */
	public Customer checkCustomerExist(String regName, String password){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from Customer where flag=0 and regName = ? and password = ?";  //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, regName);  //���ò���
			query.setParameter(1, password);
			List<Customer> list = (List<Customer>)query.list();
			
			if(list.size() == 0){
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
	 * ��鴫����û����Ƿ��ѱ�ʹ��
	 * @param regName
	 * @return
	 */
	public boolean checkRegNameUnused(String regName){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from Customer where regName = ?";  //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, regName);  //���ò���
			List<Customer> list = (List<Customer>)query.list();
			
			if(list.size() == 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	public boolean checkRegNameUnused_revise(int id, String regName){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from Customer where id != ? and regName = ?";  //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, id);
			query.setParameter(1, regName);  //���ò���
			List<Customer> list = (List<Customer>)query.list();
			
			if(list.size() == 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ���û�ע����˺���������ݿ�
	 * @param customer
	 * @return
	 */
	public boolean registerCustomer(Customer customer){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			Transaction trans = session.beginTransaction();
			int num=Integer.parseInt(session.save(customer).toString()); //�浽��������,��δ����д�����ݿ�
			trans.commit();
			
			if(num == 0){
				return false;
			}else{
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}	
	
	/**
	 * ��������޸ĺ���û����ϸ��������ݿ�
	 * @param customer
	 * @return
	 */
	public boolean reviseCustomerData(Customer customer){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.update(customer);
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
	
	/**
	 * ��������޸ĺ���û�������������ݿ�
	 * @param customer
	 * @return
	 */
	public boolean reviseCustomerPassword(Customer customer){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.update(customer);
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
	
	/**
	 * ���ݴ�����û����ؼ��������ݿ���ģ����ѯ���ܵ��û�
	 * @param nameKeyWord
	 * @return
	 */
	public List<Customer> getCustomerListByNameKeyWord(String nameKeyWord){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql=" from Customer where flag=0 and regName like ?";  //���ݿ�Ԥ�����ѯ���
			Query query = session.createQuery(hql);
			
			if(nameKeyWord != null){
				query.setParameter(0, "%" + nameKeyWord +"%");  //���ò���
			}else{
				query.setParameter(0, "%");
			}
			
			List<Customer> cusList = (List<Customer>)query.list();
			
			return cusList;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	/**
	 * ���ݴ�����û���Ų�ѯ��Ӧ���û�
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(int id){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String hql=" from Customer where flag=0 and id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			
			List<Customer> list = query.list();
			
			if(list.size() == 0){
				return null;
			}else{
				return list.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	/**
	 * ɾ��������û�
	 * @param cus
	 * @return
	 */
	public boolean deleteCustomer(Customer cus){
		Session session = null;
		try{
			cus.setFlag(1);  //�����û���ɾ���������Ϊ��ɾ��
			session = sessionFactory.openSession();
			Transaction trans = session.beginTransaction();
			session.update(cus);
			trans.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
}

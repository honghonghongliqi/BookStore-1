package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ssh.model.Administrator;
import com.ssh.model.BookComment;

public class AdministratorDao {
	SessionFactory sessionFactory;
	
	public AdministratorDao(){}
	
	/**
	 * �������Ĺ���Ա�˺������������ݿ����Ƿ����
	 * @param ad
	 * @return
	 */
	public Administrator checkAdminExist(Administrator ad){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			String queryString="from Administrator where name=? and password=?";  //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, ad.getName());  //���ò���
			query.setParameter(1, ad.getPassword());
			
			List<Administrator> list = query.list();
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
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}

package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ssh.model.BookType;

public class BookTypeDao {
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void BookTypeDao(){}
	
	public List<BookType> getAllBookType(){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			//HQL���
			String queryString="from BookType Where flag=0"; //Like '%" + bookType + "%'
			//�õ�Query����
			Query query=session.createQuery(queryString);
			//ִ�в�ѯHQL���
			List<BookType> list=(List<BookType>)query.list();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
		
	}
	
	//����µķ���
	public void addBookType(BookType bookType){
		Session session=null;
		try{
		//���session
		session=sessionFactory.openSession();
		//��һ������
		Transaction trans=session.beginTransaction();
		//�������ݣ�������Ӱ�������
		session.save(bookType);//�浽��������,��δ����д�����ݿ�
		//�ύ����
		trans.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//�ر�session
			session.close();
		}
		
	}
	
	//ɾ��ͼ�����
	public boolean deleteBookType(int typeId){
		System.out.println("������deleteBookById��ͷ");
		Session session=null;
		boolean flag = true;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			BookType booktype=(BookType)session.load(BookType.class,typeId);
			booktype.setFlag(1);
			Transaction trans=session.beginTransaction();
			session.update(booktype);
			trans.commit();
			return flag;
		}catch(Exception ex){
			System.out.println("������deleteBookById����!");
			flag = false;
			ex.printStackTrace();
			return flag;
		}finally{
			//�ر�session
			session.close();
		}	
	}
	
	public boolean reviseBookType(BookType bookType){
		//��ʱ״̬
		System.out.println("������ReviseBookType��ͷ");
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			//SQL���
			Transaction trans=session.beginTransaction();
			session.update(bookType);
			trans.commit();
			System.out.println("������ReviseBookType��β");
			return true;
		}catch(Exception ex){
			System.out.println("����ReviseBookType�Ǵ���!");
			ex.printStackTrace();
			return false;
		}finally{
			//�ر�session
			session.close();
		}
	}

}

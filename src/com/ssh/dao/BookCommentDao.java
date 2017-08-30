package com.ssh.dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import com.ssh.model.BookComment;

public class BookCommentDao {
	SessionFactory sessionFactory;
	
	public BookCommentDao(){}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * ͨ��ͼ����,��ǰ����ҳ�룬ÿҳ�������������ȡ��ͼ���Ӧҳ��ĵ����б�
	 * @param bookId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<BookComment> getBookCommentByBookIdAndPageNo(int bookId, int pageNo, int pageSize){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from BookComment where bookId=?";  //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, bookId);
			
			query.setFirstResult((pageNo-1)*pageSize); //���õ�ǰҳ����ʾ�ĵ�һ����¼������
			query.setMaxResults(pageSize); //��һҳ��ʾ�ļ�¼����
			
			List<BookComment> list = query.list();
			
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ͨ��ͼ���Ż�ȡ��ͼ������е���
	 * @param bookId
	 * @return
	 */
	public List<BookComment> getAllBookCommentByBookId(int bookId){
		Session session=null;
		try{
			session=sessionFactory.openSession(); //�õ�session����
			String queryString="from BookComment where bookId=?";   //���ݿ�Ԥ�����ѯ���
			Query query=session.createQuery(queryString);
			query.setParameter(0, bookId);  //���ò���
			
			List<BookComment> list = query.list();
			
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * �������ͼ��������������ݿ�
	 * @param bookComment
	 * @return
	 */
	public int setBookComment(BookComment bookComment){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			//��������
			Transaction trans=session.beginTransaction();
			//�������ݣ�������Ӱ�������
			int num=Integer.parseInt(session.save(bookComment).toString()); //�浽��������,��δ����д�����ݿ�
			//�ύ����
			trans.commit();
			return num;
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	/**
	 * ���ݴ����ͼ����ɾ����Ӧͼ�����������
	 * @param bookId
	 * @return
	 */
	public boolean deleteAllBookCommentByBookId(int bookId){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			
			List<BookComment> list = this.getAllBookCommentByBookId(bookId);  //��ȡ��ͼ�����������
			//��������
			Transaction trans=session.beginTransaction();
			for(int i = 0; i < list.size(); i++){  //���ɾ������
				list.get(i).setFlag(1); //��������ɾ���������Ϊ��ɾ��
				session.update(list.get(i)); //��������״̬
			}
			//�ύ����
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
}

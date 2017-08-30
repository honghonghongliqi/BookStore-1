package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import com.ssh.model.Book;

public class BookDao {
	SessionFactory sessionFactory;
	
	public void BookDao(){}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//��ȡָ����������е��鱾����
	public List<Book> getNewBooks(int bookType){
		Session session=null;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			//HQL���
			String queryString="from Book where flag=0 and typeId=? "; //Like '%" + bookType + "%'
			//�õ�Query����
			Query query=session.createQuery(queryString);
			query.setParameter(0, bookType);
			//ִ�в�ѯHQL���
			List list=(List)query.list();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			//�ر�session
			session.close();
		}
	}
	
	//NewBookҳ��ķ�ҳ����
		public List<Book> queryByPage(int pageNo,int pageSize,int bookType){//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book where flag=0 and typeId=?";
				Query query=session.createQuery(queryString);
				query.setParameter(0, bookType);
				
				//��ҳ��ѯ
				query.setFirstResult((pageNo-1)*pageSize); //������һҳ��ʾ�ĵ�һ����¼������
				query.setMaxResults(pageSize); //��һҳ��ʾ�ļ�¼����
				List<Book> list=query.list(); //ֻ����5������
				return list;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//ManageBookҳ��ķ�ҳ����
		public List<Book> manageByPage(int pageNo,int pageSize){//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0";
				Query query=session.createQuery(queryString);
				
				//��ҳ��ѯ
				query.setFirstResult((pageNo-1)*pageSize); //������һҳ��ʾ�ĵ�һ����¼������
				query.setMaxResults(pageSize); //��һҳ��ʾ�ļ�¼����
				List<Book> list=query.list(); //ֻ����5������
				return list;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//��ȡ������鱾����
		public List<Book> randomBook(int sum){
			//��ʱ״̬
			System.out.println("������randomBook��ͷ");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				//SQL���
				String queryString="Select * from Books Where flag=0 Order By NewID()"; //Like '%" + bookType + "%'
				//�õ�Query����
				SQLQuery sqlquery = session.createSQLQuery(queryString).addEntity(Book.class);
				sqlquery.setMaxResults(sum); //��һҳ��ʾ�ļ�¼����
				List<Book> list = (List<Book>)sqlquery.list(); //ֻ����3������
				System.out.println("������randomBook��β");
				return list;
			}catch(Exception ex){
				System.out.println("�����Ǵ���!");
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//��ȡȫ���鱾�ķ���
		public List<Book> getAllBook(){
			//��ʱ״̬
			System.out.println("������getAllBook��ͷ");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0";
				Query query=session.createQuery(queryString);
				System.out.println("������getAllBook��β");
				//ִ�в�ѯHQL���
				List list=(List)query.list();
				return list;
			}catch(Exception ex){
				System.out.println("������getAllBook����!");
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
			
		//ͨ��Id��ȡȫ���鱾�ķ���
		public Book queryBookById(int bookId){//��ʱ״̬
			System.out.println("������queryBookById��ͷ");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book where flag=0 and id=?";
				Query query=session.createQuery(queryString);
				query.setParameter(0, bookId);
				//ִ�в�ѯHQL���
				List<Book> list= (List<Book>)query.list();
				System.out.println("������queryBookById��β");
				return list.get(0);
			}catch(Exception ex){
				System.out.println("������queryBookById����!");
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
	//ͨ��bookIdɾ���鱾
	public boolean deleteBookById(int id){//��ʱ״̬
		System.out.println("������deleteBookById��ͷ");
		Session session=null;
		boolean flag=true;
		try{
			//���session
			session=sessionFactory.openSession(); //�õ�session����
			Book book=(Book)session.load(Book.class,id);
			book.setFlag(1);
			Transaction trans=session.beginTransaction();
			session.update(book);
			trans.commit();
		}catch(Exception ex){
			System.out.println("������deleteBookById����!");
			ex.printStackTrace();
			flag=false;
		}finally{
			//�ر�session
			session.close();
		}
		return flag;
	}

	//����ͼ��ķ���
		public boolean AddNewBook(Book book){
			//��ʱ״̬
			System.out.println("������ReviseBook��ͷ");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				//SQL���
				Transaction trans=session.beginTransaction();
				session.save(book);
				trans.commit();
				System.out.println("������ReviseBook��β");
				return true;
			}catch(Exception ex){
				System.out.println("����ReviseBook�Ǵ���!");
				ex.printStackTrace();
				return false;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//�޸�ͼ��ķ���
		public boolean ReviseBook(Book book){
			//��ʱ״̬
			System.out.println("������ReviseBook��ͷ");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				//SQL���
				Transaction trans=session.beginTransaction();
				session.update(book);
				trans.commit();
				System.out.println("������ReviseBook��β");
				return true;
			}catch(Exception ex){
				System.out.println("����ReviseBook�Ǵ���!");
				ex.printStackTrace();
				return false;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		/*
		 * ͨ��ͼ���Ż�ȡĿ��ͼ��ʵ��
		 */
		public Book getBookByBookId(int bookId){
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book where flag=0 and id=?";
				Query query=session.createQuery(queryString);
				query.setParameter(0, bookId);
				return (Book)query.list().get(0);
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//��ȡ�������鼮
		public List<Book> SerachBook(String bookName){
			//��ʱ״̬
			System.out.println("SerachBook��ͷ��");
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0 and name Like ?";//ģ����ѯ
				Query query=session.createQuery(queryString);
				query.setParameter(0, "%"+bookName+"%");//��ֵ��name
				System.out.println("SerachBook��β");
				//ִ�в�ѯHQL���
				List list=(List)query.list();
				return list;
			}catch(Exception ex){
				System.out.println("SerachBook����!");
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}	
		}
		
		//ManageBookҳ��ķ�ҳ����
		public List<Book> manageBookByPage(int pageNo,int pageSize,String bookName){
			//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0 and name Like ?";//ģ����ѯ
				Query query=session.createQuery(queryString);
				query.setParameter(0, "%"+bookName+"%");//��ֵ��name
				
				//��ҳ��ѯ
				query.setFirstResult((pageNo-1)*pageSize); //������һҳ��ʾ�ĵ�һ����¼������
				query.setMaxResults(pageSize); //��һҳ��ʾ�ļ�¼����
				List<Book> list=query.list(); //ֻ����5������
				return list;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//��ȡ�²�Ʒ�鼮
		public List<Book> getNewProduct(){
			//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0 Order by id Desc";//��������
				Query query=session.createQuery(queryString);
				
				query.setMaxResults(10); //��һҳ��ʾ�ļ�¼����
				List<Book> list=query.list(); //ֻ����510������
				return list;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
		
		//��ȡ����˵��鼮
		public List<Book> cheapBook(){
			//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0 Order by price Asc";//��������
				Query query=session.createQuery(queryString);
				
				query.setMaxResults(10); //��һҳ��ʾ�ļ�¼����
				List<Book> list=query.list(); //ֻ����510������
				return list;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
			
		}
		
		//�ж�ISBN�Ƿ����
		public boolean checkISBNExist(String isbn){
			//��ʱ״̬
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book Where flag=0 and isbn=?";//��������
				Query query=session.createQuery(queryString);
				
				query.setParameter(0, isbn);
				List<Book> list=query.list(); //ֻ����510������
				if(list.size()<1)
					return false;
				else
					return true;
			}catch(Exception ex){
				ex.printStackTrace();
				return true;
			}finally{
				//�ر�session
				session.close();
			}
			
		}

		//ͨ��ͼ���Ż�ȡĿ��ͼ��ʵ��
		public Book getBookByBookIdWCH(int bookId){
			Session session=null;
			try{
				//���session
				session=sessionFactory.openSession(); //�õ�session����
				String queryString="from Book where id=? and flag = 0";
				Query query=session.createQuery(queryString);
				query.setParameter(0, bookId);
				List list = query.list();
				if(list == null || list.size() == 0){
					System.out.println("�鲻���ڻ��ѱ�ɾ��");//
					return null;
				}
				return (Book)list.get(0);
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}finally{
				//�ر�session
				session.close();
			}
		}
}

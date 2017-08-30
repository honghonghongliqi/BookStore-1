package com.ssh.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import com.ssh.model.Book;

public class NewBookAction extends ActionSupport{
	BookDao bd;
	List<Book> books;
	private final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	private int pageNo=1; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage; //��ҳ��
	private int bookType;


	public int getBookType() {
		return bookType;
	}

	public void setBookType(int bookType) {
		this.bookType = bookType;
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public BookDao getBd() {
		return bd;
	}

	public void setBd(BookDao bd) {
		this.bd = bd;
	}

	
	public String execute() throws Exception{
		//��ȡ�����û�����Ϣ
		System.out.println("����excute");
		System.out.println(bookType);
		books = bd.getNewBooks(bookType);
		
		if(books.size()<=0)
			return INPUT;

		//������������ÿҳ��ʾ������������������ҳ��
		if(books.size()%pageSize==0){
			totalPage=books.size()/pageSize;
		}else{
			totalPage=books.size()/pageSize+1;
		}
		if(pageNo<=0){
			pageNo=totalPage;
		}else if(pageNo>totalPage){
			pageNo=1;
		}

		//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ������
		books=bd.queryByPage(pageNo,pageSize,bookType);
		currentPage=pageNo;
		
		System.out.println("��ǰҳ��"+currentPage);
		System.out.println("��ҳ����"+totalPage);
		
		System.out.println(books.get(0).getImage());
		return SUCCESS;
	}
	
	
}

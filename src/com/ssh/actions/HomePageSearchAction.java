package com.ssh.actions;

import java.util.List;

import javax.swing.JOptionPane;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import com.ssh.model.Book;

public class HomePageSearchAction extends ActionSupport{
	String bookName;//��ȡ�������鼮����
	BookDao bd;//�鼮���ݿ�
	List<Book> searchBooks;//�����鼮�б�

	private final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	private int pageNo=1; //������,�ӵ�1ҳ��ʼ
	private int currentPage; //��ǰҳ
	private int totalPage; //��ҳ��
	
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BookDao getBd() {
		return bd;
	}
	public void setBd(BookDao bd) {
		this.bd = bd;
	}
	public List<Book> getSearchBooks() {
		return searchBooks;
	}
	public void setSearchBooks(List<Book> searchBooks) {
		this.searchBooks = searchBooks;
	}
	
	public String execute()throws Exception{
		bookName = new String(bookName.getBytes("iso-8859-1"),"UTF-8");
		System.out.println("-----------bookName��------------"+bookName);
		searchBooks = bd.SerachBook(bookName);
		if(searchBooks.size()<1){
			JOptionPane.showMessageDialog(null, "����������Ʒ����û�У�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return INPUT;
		}
		else{
			//������������ÿҳ��ʾ������������������ҳ��
			if(searchBooks.size()%pageSize==0){
				totalPage=searchBooks.size()/pageSize;
			}else{
				totalPage=searchBooks.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo=1;
			}else if(pageNo>totalPage){
				pageNo=totalPage;
			}

			//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ������
			searchBooks=bd.manageBookByPage(pageNo,pageSize,bookName);
			currentPage=pageNo;
		}
		return SUCCESS;
	}
}

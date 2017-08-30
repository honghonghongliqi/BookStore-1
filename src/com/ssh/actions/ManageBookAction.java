package com.ssh.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import com.ssh.model.Book;

public class ManageBookAction extends ActionSupport{
	String bookName;//��ȡ�������鼮����
	BookDao bd;//�鼮���ݿ�
	List<Book> manageBooks;//�����鼮�б�
	
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
	public List<Book> getManageBooks() {
		return manageBooks;
	}
	public void setManageBooks(List<Book> manageBooks) {
		this.manageBooks = manageBooks;
	}
	
	//��ȡ�鼮�б�ͻ�ȡ����������
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		if(bookName!=null){
			bookName = new String(bookName.getBytes("iso-8859-1"),"UTF-8");
			System.out.println("-----------bookName��------------"+bookName);
			manageBooks = bd.SerachBook(bookName);
			if(manageBooks.size()<1){
				JOptionPane.showMessageDialog(null, "���������鼮�����ڣ�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return LOGIN;
			}
			else{
				//������������ÿҳ��ʾ������������������ҳ��
				if(manageBooks.size()%pageSize==0){
					totalPage=manageBooks.size()/pageSize;
				}else{
					totalPage=manageBooks.size()/pageSize+1;
				}
				if(pageNo<=0){
					pageNo=1;
				}else if(pageNo>totalPage){
					pageNo=totalPage;
				}

				//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ������
				manageBooks=bd.manageBookByPage(pageNo,pageSize,bookName);
				currentPage=pageNo;
			}
		}else{
			manageBooks = bd.getAllBook();
			
			//������������ÿҳ��ʾ������������������ҳ��
			if(manageBooks.size()%pageSize==0){
				totalPage=manageBooks.size()/pageSize;
			}else{
				totalPage=manageBooks.size()/pageSize+1;
			}
			if(pageNo<=0){
				pageNo=1;
			}else if(pageNo>totalPage){
				pageNo=totalPage;
			}

			//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ������
			manageBooks=bd.manageByPage(pageNo,pageSize);
			currentPage=pageNo;
		}
		request.setAttribute("manageBooks", manageBooks);
		return SUCCESS;
		
	}

}

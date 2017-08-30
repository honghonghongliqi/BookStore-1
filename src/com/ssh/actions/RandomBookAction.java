package com.ssh.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import com.ssh.model.Book;

public class RandomBookAction extends ActionSupport{
	BookDao bd;
	List<Book> randomBooks;
	int bookSum;
	public BookDao getBd() {
		return bd;
	}
	public void setBd(BookDao bd) {
		this.bd = bd;
	}
	public List<Book> getRandomBooks() {
		return randomBooks;
	}
	public void setRandomBooks(List<Book> randomBooks) {
		this.randomBooks = randomBooks;
	}
	public int getBookSum() {
		return bookSum;
	}
	public void setBookSum(int bookSum) {
		this.bookSum = bookSum;
	}
	public String execute() throws Exception{
		System.out.println("bookSum:"+bookSum);
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ȡ�����û�����Ϣ
		System.out.println("����RandomBook��excute");
		//ִ�����ѡ��
		randomBooks = bd.randomBook(bookSum);
		//��ȡ�鱾��ͼƬ����
		request.setAttribute("randomBooks", randomBooks);
		System.out.println("�����ͼƬ:"+randomBooks.get(0).getImage());
		return SUCCESS;
	}

}

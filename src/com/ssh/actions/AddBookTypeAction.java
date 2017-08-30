package com.ssh.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookTypeDao;
import com.ssh.model.BookType;

public class AddBookTypeAction extends ActionSupport{
	BookTypeDao btd;
	BookType bookType;
	
	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public BookTypeDao getBtd() {
		return btd;
	}

	public void setBtd(BookTypeDao btd) {
		this.btd = btd;
	}
	
	public void validate(){
		System.out.println("����AddBookTypeAction��validate");
		if(bookType.getTypeName().equals("")||bookType.getTypeName()==null){
			this.addFieldError("errorTypeName", "*������Ϊ�գ�");
			System.out.println("����:"+bookType.getTypeName());
		}
	}
	
	public String execute() throws Exception{
		btd.addBookType(bookType);
		return SUCCESS;
	}
}

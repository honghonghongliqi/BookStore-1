package com.ssh.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookTypeDao;
import com.ssh.model.BookType;

public class ReviseBookTypeAction extends ActionSupport{
	BookType bookType;
	BookTypeDao btd;
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
		System.out.println("������ReviseBookType��validate");
		if(bookType.getTypeName().equals("")||bookType.getTypeName()==null){
			System.out.println("errorRevise����Ϊ��");
			this.addFieldError("errorRevise", "*����Ϊ��");
		}
	}
	
	public String execute()throws Exception{
		bookType.setTypeName(new String(bookType.getTypeName().getBytes("iso-8859-1"),"UTF-8"));
		System.out.println("TypeId:"+bookType.getTypeId());
		System.out.println("TypeName:"+bookType.getTypeName());
		boolean flag = btd.reviseBookType(bookType);
		if(flag==true)
			return SUCCESS;
		else
			return INPUT;
	}

}

package com.ssh.actions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.dao.BookDao;
import com.ssh.model.Book;

public class ReviseBookAction extends ActionSupport{
	//����˽������
	private File upload;
	private String uploadFileName; //�ļ���
	private String uploadContentType; //�ļ�����
	
	Book book;
	BookDao bd;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public BookDao getBd() {
		return bd;
	}
	public void setBd(BookDao bd) {
		this.bd = bd;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	//�����ļ�
	public void uploadFile(File target, File source){
		try{
			//��������������
			FileInputStream fis=new FileInputStream(source);
			DataInputStream dis=new DataInputStream(fis);
			
			//�������������
			FileOutputStream fos=new FileOutputStream(target);
			DataOutputStream dos=new DataOutputStream(fos);
			int temp;
			
			while((temp=dis.read())!=-1){
				dos.write(temp);
			}
			//�ر�
			dis.close();
			fis.close();
			dos.close();
			fos.close();
		}catch(FileNotFoundException ex){
			System.out.println("�ļ��Ҳ���");
			ex.printStackTrace();
		}catch(IOException ex){
			System.out.println("�ļ���д�쳣");
			ex.printStackTrace();
		}
	}

	public void validate(){
		System.out.println("����ReviseBookAction��validate");
		if(book.getName().equals("")){
			System.out.println("----------------");
			this.addFieldError("errorName", "*������Ϊ�գ�");
		}
		if(book.getAuthor().equals("")){
			System.out.println("----------------");
			this.addFieldError("errorAuthor", "*������Ϊ�գ�");
		}
		if(book.getPrice()<=0){
			System.out.println("----------------");
			this.addFieldError("errorPrice", "*����Ϊ������");
		}
		if(book.getPublisher().equals("")){
			System.out.println("----------------");
			this.addFieldError("errorPublisher", "*������Ϊ�գ�");
		}
		if(book.getIsbn().equals("")){
			System.out.println("----------------");
			this.addFieldError("errorISBN", "*������Ϊ�գ�");
		}
	}
	
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	}
	
	public String execute() throws Exception{
		//��ȡ�����û�����Ϣ
		System.out.println("����ReviseBookAction��excute");
		System.out.println("ͼ��bookId:"+book.getId());
		System.out.println("ͼ��bookType:"+book.getTypeId());
		System.out.println("ͼ��bookPageCount:"+book.getPageCount());
		System.out.println("ͼ��bookWordCount:"+book.getWordCount());
		System.out.println("ͼ��bookImage:"+book.getImage());

		System.out.println("File:"+upload);
		System.out.println("�ļ���:"+uploadFileName);
		System.out.println("�ļ�����:"+uploadContentType);
		
		if(upload!=null){
			uploadFileName = getRandomString(10)+".jpg";
			
			book.setImage(uploadFileName);
			
			//��ȡ�ļ��ϴ�·��,�������upload��ָ�ļ���upload����������ļ�upload
			String filePath=ServletActionContext.getServletContext().getRealPath("img/books")
	        +"\\"+this.uploadFileName;
			
			System.out.println("�ļ�λ�ã�"+filePath);
			
			File target=new File(filePath);
			//����uploadFile���������ļ�����
			uploadFile(target,upload);
		}
		if(book.getIntroduction()==null||book.getIntroduction().equals(""))
			book.setIntroduction("��");
		
		bd.ReviseBook(book);
		
		return LOGIN;
	}
}

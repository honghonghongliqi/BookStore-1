package com.ssh.model;

public class Book {
	private int id;  //ͼ����
	private String name;  //ͼ������
	private float price;  //ͼ�鵥��
	private int stockStatus;  //ͼ����
	private String publisher;  //ͼ�������
	private String author;  //ͼ������
	private int typeId;  //ͼ������
	private String isbn;  //���ʱ�׼ͼ����
	private int pageCount;  //ͼ��ҳ��
	private int wordCount;  //ͼ������
	private String introduction;  //ͼ����
	private String image;  //ͼ��ͼƬ
	private int flag;  //ͼ��ɾ�����

	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}

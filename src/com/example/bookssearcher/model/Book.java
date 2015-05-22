package com.example.bookssearcher.model;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String description;
	//Additional fields
	private String downloadLink;
	private String coverLink;
	private String year;
	private String pages;
	private String publisher;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public String getCoverLink() {
		return coverLink;
	}
	public void setCoverLink(String coverLink) {
		this.coverLink = coverLink;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Book(String id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	
	
	
	

	
	
	
}

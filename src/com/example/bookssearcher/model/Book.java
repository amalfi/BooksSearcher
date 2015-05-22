package com.example.bookssearcher.model;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Book(String title, String description)
	{
		super();
		this.title = title;
		this.description = description;
	}
	

	
	
	
}

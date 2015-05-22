package com.example.bookssearcher.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Serializable {

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

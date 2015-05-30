package com.example.bookssearcher.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.example.bookssearcher.model.Book;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseManager
{
	  private final String dbName = "BooksDatabase";
	    private final String tableName = "Books";
		
    public List<Book> getAllBooksFromDatabase(SQLiteDatabase sampleDB)
    {
    	List<Book> listWithBooks = new ArrayList<Book>();
    	LinkedHashSet<HashMap<String,Object>> result = selectFromDatabase(sampleDB, "SELECT id, title, description FROM " + tableName);
    	for(HashMap<String,Object> currentResultMap :  result)
    	{
    		String id = String.valueOf(currentResultMap.get("id"));
    		String title = String.valueOf(currentResultMap.get("title"));
    		String description = String.valueOf(currentResultMap.get("description"));
    		Book book = new Book(id, title, description);
    		
    		listWithBooks.add(book);
    	}
    	return listWithBooks;
    	
    }
    /**
     * Metoda pobierajaca wyniki z bazy na podstawie query
     * @param sampleDB
     * @param query //"SELECT title, description FROM " + tableName - query example
     */
    public LinkedHashSet<HashMap<String,Object>> selectFromDatabase(SQLiteDatabase sampleDB, String query)
    {
    	LinkedHashSet<HashMap<String, Object>> result = new LinkedHashSet<HashMap<String,Object>>();
    	try
    	{
	        Cursor c = sampleDB.rawQuery(query, null);
	        if (c != null )
	        {
	            if  (c.moveToFirst()) 
	            {
	                do
	                {
	                	String id = c.getString(c.getColumnIndex("id"));
	                    String title = c.getString(c.getColumnIndex("title"));
	                    String description = c.getString(c.getColumnIndex("description"));
	                    
	                    HashMap<String,Object> currentResultMap = new HashMap<String,Object>();
	                    currentResultMap.put("id", id);
	                    currentResultMap.put("title", title);
	                    currentResultMap.put("description", description);
	                    result.add(currentResultMap);
	                }
	                while (c.moveToNext()); //Move to next row
	            } 
	        }
    	}
    	catch (SQLiteException se ) 
 	   {
 			System.out.println(se.getStackTrace());
 			Log.d("SecondActivity", "Wystapil blad podczas polecenia select", se.getCause());
 			Log.i("Error : " , "Message is :- " + se.getMessage());
 			sampleDB.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
 	   finally 
        {
            if (sampleDB != null) 
            {
                sampleDB.close();
            }
        }
     
       return result;
    }
    /**
     * Function delete from db by query and return new list of objects
     * @param database
     * @param query
     * @return
     */
   public LinkedHashSet<HashMap<String,Object>> deleteFromDatabase(SQLiteDatabase database,String query)
   {
	   LinkedHashSet<HashMap<String, Object>> result = new LinkedHashSet<HashMap<String,Object>>();
	   try
	   {
		   database.execSQL(query);
	       Cursor c = database.rawQuery("SELECT title, description FROM " + tableName, null);
	       if (c != null )
	       {
	           if  (c.moveToFirst()) 
	           {
	               do
	               {
	            	   //String id=c.getString(c.getColumnIndex("id"));
	                   String title = c.getString(c.getColumnIndex("title"));
	                   String description = c.getString(c.getColumnIndex("description"));
	                   
	                   HashMap<String,Object> currentResultMap = new HashMap<String,Object>();
	                   //currentResultMap.put("id", id);
	                   currentResultMap.put("title", title);
	                   currentResultMap.put("description", description);
	                   result.add(currentResultMap);
	               }
	               while (c.moveToNext()); //Move to next row
	           } 
	       }
       
	   }
	   catch (SQLiteException se ) 
	   {
			System.out.println(se.getStackTrace());
			Log.d("SecondActivity", "Wystapil blad podczas polecenia select", se.getCause());
			Log.i("Error : " , "Message is :- " + se.getMessage());
			database.execSQL("DROP TABLE IF EXISTS " + tableName);
       }
	   finally 
       {
           if (database != null) 
           {
               database.close();
           }
       }
    
	   return result;
   }
    
}

package com.example.bookssearcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookssearcher.db.DatabaseManager;
import com.example.bookssearcher.model.Book;
import com.example.bookssearcher.model.DataHolder;
import com.example.bookssearcher.request.RequestService;

/**
 * Class with few functions used in application
 * @author Marcin Berendt
 *
 */
public class Utils 
{
	private final String dbName = "BooksDatabase";
    private final String tableName = "Books";
    
	/**
	 * 
	 * @return
	 */
	public String[] getTitlesOfBooksSavedInDatabase(LinkedHashSet<HashMap<String, Object>> resultFromQuery)
	{	
		String[] titles = new String[resultFromQuery.size()];
		int i=0;
		for(HashMap<String,Object> currentMap : resultFromQuery)
		{
			String title = String.valueOf(currentMap.get("title"));
			titles[i]=title;
			i+=1;
		}
		
		return titles;
	}
	
	/**
	 * Function set additional fields in selected book object, based on selected book id (alos in data holder)
	 * @return
	 */
	public Book getBookObjectWithDetails()
	{
		 Book book = null;
		 try 
         {
			    RequestService requestService = new RequestService();
		        Book selectedBook = DataHolder.getSelectedBook();
		        String selectedBookId = selectedBook.getId();
		        String jsonWithBookDetails = requestService.getBookDetailsByID(selectedBookId);
		        if(jsonWithBookDetails.contains("Book not found!")!=true)
		        {
		        	JSONObject item = new JSONObject(jsonWithBookDetails);
					
					String title = item.getString("Title");
				    String description= item.getString("Description");
				    String id = item.getString("ID");
				    book = new Book(id, title, description);
				    //Book details
				    String year = item.getString("Year");
				    String pages = item.getString("Page");
				    String publisher = item.getString("Publisher");
				    String downloadLink = item.getString("Download");
				    String coverLink = item.getString("Image");
				    
				    book.setYear(year);
				    book.setPages(pages);
				    book.setPublisher(publisher);
				    book.setDownloadLink(downloadLink);
				    book.setCoverLink(coverLink);
		        }
		  } 
         catch (JSONException e) 
        {
         	 Log.d("Wystapil blad podczas tworzenia obiektu JSON", Log.getStackTraceString(e.getCause().getCause()));
		}
		return book;
	}
	public String[] getTitlesArray()
	{
        final List<Book> books = DataHolder.getBookList();
        String[] onlyTitles = new String[books.size()];
        for(int i=0; i<onlyTitles.length; i++)
        {
        	Book currentBook = books.get(i);
        	onlyTitles[i]=currentBook.getTitle();
        }
        
        return onlyTitles;
	}
	
	/**
	 * Function make request to itebooks.info api and set list of books objects based on json response
	 * @param bookKeyword
	 */
	public void setBooksListBasedOnKeyword(String bookKeyword)
	{
		RequestService requestService = new RequestService();
		String jsonResponse = requestService.getBooksBasedOnKeyword(bookKeyword);
        List<Book> books = new ArrayList<Book>();
        try 
        {
			JSONObject obj = new JSONObject(jsonResponse);
			JSONArray arr = obj.getJSONArray("Books");
			for (int i=0; i<arr.length(); i++)
			{
			    JSONObject item = arr.getJSONObject(i);
			    String title = item.getString("Title");
			    String description= item.getString("Description");
			    String id = item.getString("ID");
			    Book book = new Book(id, title, description);
			    books.add(book);
			}
		} 
        catch (JSONException e) 
        {
        	 Log.d("Wystapil blad podczas tworzenia obiektu JSON", Log.getStackTraceString(e.getCause().getCause()));
		}
        
        //secondActivityIntent.putExtra("titles", titles.toString());
        DataHolder.setBookList(books);
        
	}
}

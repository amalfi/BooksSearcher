package com.example.bookssearcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

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
	public Book getBookObjectWithDetails()
	{
		 Book book = null;
		 try 
         {
			    RequestService requestService = new RequestService();
		        Book selectedBook = DataHolder.getSelectedBook();
		        String selectedBookId = selectedBook.getId();
		        String jsonWithBookDetails = requestService.getBookDetailsByID(selectedBookId);
		        
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
         catch (JSONException e) 
        {
         	 Log.d("Wystapil blad podczas tworzenia obiektu JSON", Log.getStackTraceString(e.getCause().getCause()));
		}
		return book;
	}
}

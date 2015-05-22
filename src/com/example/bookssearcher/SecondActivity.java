package com.example.bookssearcher;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookssearcher.model.Book;
import com.example.bookssearcher.model.DataHolder;
import com.example.musicalbumsinformationmanager.R;


public class SecondActivity extends Activity
{

    private final String dbName = "BooksDatabase";
    private final String tableName = "Books";
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        
        final Button saveToDatabaseButton = (Button) findViewById(R.id.saveToDatabaseButton);
        final Button stepBackButton = (Button) findViewById(R.id.stepBackButton);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        final EditText bookDescriptionEditText = (EditText) findViewById(R.id.bookDescriptionEditText);
        
        final SQLiteDatabase sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);   

        saveToDatabaseButton.setOnClickListener(new View.OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				try
				{
		            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (versionname VARCHAR);");
		            sampleDB.execSQL("INSERT INTO " + tableName + " Values ('"+DataHolder.getSelectedBook().getTitle()+"','"+DataHolder.getSelectedBook().getDescription()+"');");
		  
		        } 
				catch (SQLiteException se ) 
				{
		            Toast.makeText(getApplicationContext(), "Couldn't create or open the database", Toast.LENGTH_LONG).show();
		        } finally 
		        {
		            if (sampleDB != null) 
		            {
		                //sampleDB.execSQL("DELETE FROM " + tableName);
		                sampleDB.close();
		            }
		        }
			}
		});
        
        stepBackButton.setOnClickListener(new View.OnClickListener()
        {
	        @Override
	        public void onClick(View v)
	        {
	        	 startActivity(mainActivityIntent);
	        }
        });
        

        final List<Book> books = DataHolder.getBookList();
        String[] onlyTitles = new String[books.size()];
        for(int i=0; i<onlyTitles.length; i++)
        {
        	Book currentBook = books.get(i);
        	onlyTitles[i]=currentBook.getTitle();
        }
        
        Spinner dropdown = (Spinner)findViewById(R.id.albumsDropdownList);
        final String[] items = onlyTitles;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        
        
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() 
        {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) 
			{
				Book selectedBook = books.get(position);
				bookDescriptionEditText.setText(selectedBook.getDescription());
				DataHolder.setSelectedBook(selectedBook);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				Book selectedBook = books.get(0);
				bookDescriptionEditText.setText(selectedBook.getDescription());
				DataHolder.setSelectedBook(selectedBook);
			}        
        });
    }


}

package com.example.bookssearcher;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
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
    
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        
        final Button saveToDatabaseButton = (Button) findViewById(R.id.saveToDatabaseButton);
        final Button stepBackButton = (Button) findViewById(R.id.stepBackButton);
        final Button showAllSavedBooks = (Button) findViewById(R.id.showALlSavedBooksButton);
        final Button showDetailsOfSelectedBook = (Button) findViewById(R.id.showDetailsButton);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        final Intent thirdActivityIntent = new Intent(this, ThirdActivity.class);
        final Intent fourthActivityIntent = new Intent(this, FourthActivity.class);
        final AlertDialog alertDialog = new AlertDialog.Builder(SecondActivity.this).create();
        alertDialog.setTitle("Insert confirmation");
        alertDialog.setMessage("Selected book was added to database");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
        {
                public void onClick(DialogInterface dialog, int which) 
                {
                Toast.makeText(getApplicationContext(), "Confirmed", Toast.LENGTH_SHORT).show();
                }
        });
 
    
        final EditText bookDescriptionEditText = (EditText) findViewById(R.id.bookDescriptionEditText);
        final SQLiteDatabase sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);   
        
        showDetailsOfSelectedBook.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				startActivity(fourthActivityIntent);
			}
		});
        
        showAllSavedBooks.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View arg0) 
			{
				startActivity(thirdActivityIntent);
			}
        });
        
        saveToDatabaseButton.setOnClickListener(new View.OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				try
				{
		            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (id VARCHAR, title VARCHAR, description VARCHAR);");
		            Book selectedBook = DataHolder.getSelectedBook();
		            String selectedBookId = selectedBook.getId();
		            String selectedBookTitle = selectedBook.getTitle();
		            String selectedBookDescription = selectedBook.getDescription();
		            
		            sampleDB.execSQL("INSERT INTO " + tableName + " Values ('"+selectedBookId+"','" + selectedBookTitle+"','"+selectedBookDescription+"');");
		            // Showing Alert Message
		            alertDialog.show();
		            
		        } 
				catch (SQLiteException se ) 
				{
					System.out.println(se.getStackTrace());
					Log.d("SecondActivity", "Wystapil blad podczas polecenia INSERT", se.getCause());
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

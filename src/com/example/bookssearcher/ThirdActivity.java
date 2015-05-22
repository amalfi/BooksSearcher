package com.example.bookssearcher;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookssearcher.db.DatabaseManager;
import com.example.musicalbumsinformationmanager.R;


public class ThirdActivity extends Activity
{
    private final String dbName = "BooksDatabase";
    private final String tableName = "Books";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
     
        DatabaseManager databaseManager = new DatabaseManager();
        final Button stepBackToSecondActivity = (Button) findViewById(R.id.stepBackOnThirdActivityButton);
        final Intent secondActivityIntent = new Intent(this, SecondActivity.class);
        final EditText selectedTitleEditText = (EditText) findViewById(R.id.savedBookDescriptionEditText);
        final SQLiteDatabase sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);   
        final LinkedHashSet<HashMap<String,Object>> resultFromDatabase = databaseManager.selectFromDatabase(sampleDB, "SELECT title, description FROM " + tableName);
        final String[] titlesArray = new String[resultFromDatabase.size()];
        int i=0;
        
        for(HashMap<String,Object> currentMap : resultFromDatabase)
        {
        	String currentTitle = String.valueOf(currentMap.get("title"));
        	titlesArray[i]=currentTitle;
        	i+=1;
        }
        
        Spinner dropdown = (Spinner)findViewById(R.id.savedAlbumsDropdownList);
        final String[] items = titlesArray;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) 
			{
			  String selectedTitle = titlesArray[position];
			  for(HashMap<String,Object> currentMap : resultFromDatabase)
	          {
		        	String currentTitle = String.valueOf(currentMap.get("title"));
		        	if(currentTitle.equals(selectedTitle))
		        	{
		        		selectedTitleEditText.setText(currentMap.get("description").toString());
		        	}
	          }
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
			}        
        });
        
        stepBackToSecondActivity.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View arg0) 
			{
				startActivity(secondActivityIntent);
			}
        });
    }


}

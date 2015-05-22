package com.example.bookssearcher.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

public class DatabaseManager
{

    
    /**
     * Metoda pobierajaca wyniki z bazy na podstawie query
     * @param sampleDB
     * @param query //"SELECT title, description FROM " + tableName - query example
     */
    public List<HashMap<String,Object>> selectFromDatabase(SQLiteDatabase sampleDB, String query)
    {
    	List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
    	
        Cursor c = sampleDB.rawQuery(query, null);
        //If Cursor is valid
        if (c != null )
        {
            //Move cursor to first row
            if  (c.moveToFirst()) 
            {
                do
                {
                    String title = c.getString(c.getColumnIndex("title"));
                    String description = c.getString(c.getColumnIndex("description"));
                    
                    HashMap<String,Object> currentResultMap = new HashMap<String,Object>();
                    currentResultMap.put("title", title);
                    currentResultMap.put("description", description);
                    result.add(currentResultMap);
                }
                while (c.moveToNext()); //Move to next row
            } 
        }
     
       return result;
    }
   
    
}

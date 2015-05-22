package com.example.bookssearcher.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager
{

    
    /**
     * Metoda pobierajaca wyniki z bazy na podstawie query
     * @param sampleDB
     * @param query //"SELECT title, description FROM " + tableName - query example
     */
    public LinkedHashSet<HashMap<String,Object>> selectFromDatabase(SQLiteDatabase sampleDB, String query)
    {
    	LinkedHashSet<HashMap<String, Object>> result = new LinkedHashSet<HashMap<String,Object>>();
    	
        Cursor c = sampleDB.rawQuery(query, null);
        if (c != null )
        {
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

package com.example.bookssearcher.request;

/**
 * Created by Marcin on 2015-05-18.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;
import android.util.Log;

/**
 * @author Marcin Berendt
 * Simple REST Client to get response from lastFM API
 */
public class RequestService
{
    @SuppressWarnings("deprecation")
	public  String getBooksBasedOnKeyword(String bookTitle)
    {
	        //Absoultelly necessary to avoid 'NetworkOnMainThread Exception'
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
        	
            String sUrl = "http://it-ebooks-api.info/v1/search/"+bookTitle;
            String result = "";
            StringBuffer chaine = new StringBuffer("");
            try{
                URL url = new URL(sUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("User-Agent", "");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) 
                {
                    chaine.append(line);
                }
                result = chaine.toString();

            } catch (IOException e)
            {
            	 Log.d("Wystapil blad podczas uzyskiwania odpwoiedzi z serwera", Log.getStackTraceString(e.getCause().getCause()));
            }

        return result;
    }

    /**
     * 
     * @param bookId
     * @return
     */
    public  String getBookDetailsByID(String bookId)
    {
	        //Absoultelly necessary to avoid 'NetworkOnMainThread Exception'
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
        	
            String sUrl = "http://it-ebooks-api.info/v1/book/"+bookId;
            String result = "";
            StringBuffer chaine = new StringBuffer("");
            try{
                URL url = new URL(sUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("User-Agent", "");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) 
                {
                    chaine.append(line);
                }
                result = chaine.toString();

            } catch (IOException e)
            {
            	 Log.d("Wystapil blad podczas uzyskiwania odpwoiedzi z serwera", Log.getStackTraceString(e.getCause().getCause()));
            }

        return result;
    }

  
   
}
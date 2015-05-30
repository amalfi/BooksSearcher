package com.example.bookssearcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookssearcher.model.Book;
import com.example.musicalbumsinformationmanager.R;

/**
 * Activity which contains details about selected book 
 * @author Marcin
 *
 */
public class FourthActivity extends Activity
{
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);
     
        final Button stepBackButton = (Button) findViewById(R.id.stepBackOnFourthActivityButton);
        final Intent secondActivityIntent = new Intent(this, SecondActivity.class);
        final TextView yearTextView = (TextView) findViewById(R.id.year);
        final TextView titleTextView = (TextView) findViewById(R.id.title);
        final TextView pagesTextView = (TextView) findViewById(R.id.pages);
        final TextView publisherTextView = (TextView) findViewById(R.id.publisher);
        final TextView downloadLinkTextView = (TextView) findViewById(R.id.downloadLink);
        final TextView coverTextView = (TextView) findViewById(R.id.coverLink);
      
        final AlertDialog alertDialog = new AlertDialog.Builder(FourthActivity.this).create();
        alertDialog.setTitle("Book not found!");
        alertDialog.setMessage("Book not found !");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
        {
                public void onClick(DialogInterface dialog, int which) 
                {
                Toast.makeText(getApplicationContext(), "Confirmed", Toast.LENGTH_SHORT).show();
                }
        });
        
        Utils utils = new Utils();
        Book book = utils.getBookObjectWithDetails();
        if(book!=null)
        {
	        yearTextView.setText(yearTextView.getText()+" " + book.getYear());
	        titleTextView.setText(titleTextView.getText()+ " "+ book.getTitle());
	        pagesTextView.setText(pagesTextView + " " + book.getPages());
	        publisherTextView.setText(publisherTextView.getText() + " "  + book.getPublisher());
	        downloadLinkTextView.setText(downloadLinkTextView.getText() + " " + book.getDownloadLink());
	        coverTextView.setText(coverTextView.getText() + " " + book.getCoverLink());
        }
        else if(book==null)
        {
        	alertDialog.show();
        }
        
        stepBackButton.setOnClickListener(new View.OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				startActivity(secondActivityIntent);
			}
		});
    }
}
	
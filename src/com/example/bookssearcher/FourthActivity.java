package com.example.bookssearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookssearcher.model.Book;
import com.example.bookssearcher.model.DataHolder;
import com.example.bookssearcher.request.RequestService;
import com.example.musicalbumsinformationmanager.R;


public class FourthActivity extends Activity
{
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
        
        Utils utils = new Utils();
        Book book = utils.getBookObjectWithDetails();
        
        yearTextView.setText(yearTextView.getText()+" " + book.getYear());
        titleTextView.setText(titleTextView.getText()+ " "+ book.getTitle());
        pagesTextView.setText(pagesTextView + " " + book.getPages());
        publisherTextView.setText(publisherTextView.getText() + " "  + book.getPublisher());
        downloadLinkTextView.setText(downloadLinkTextView.getText() + " " + book.getDownloadLink());
        coverTextView.setText(coverTextView.getText() + " " + book.getCoverLink());
        //to be finished....
        
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
	
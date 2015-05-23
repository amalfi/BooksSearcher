package com.example.bookssearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
	
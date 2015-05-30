package com.example.bookssearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musicalbumsinformationmanager.R;

/**
 * Main activity with input text field to find books by keyword
 * @author Marcin
 *
 */
public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button findAlbumsButton = (Button) findViewById(R.id.findAlbumsButton);
        final EditText bookKeywordEditText = (EditText) findViewById(R.id.artistNameEditText);
        final Intent secondActivityIntent = new Intent(this, SecondActivity.class);
        final Utils utils = new Utils();

        findAlbumsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String bookKeyword = bookKeywordEditText.getText().toString();
                utils.setBooksListBasedOnKeyword(bookKeyword);
                startActivity(secondActivityIntent);
            }
        });


    }


}

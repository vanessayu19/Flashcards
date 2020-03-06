package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        // close out of new page
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // dismisses this current activity
            }
        });

        // save and close new card
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.newQuestion)).getText().toString();

                // create data to send back when this activity is closed
                Intent data = new Intent();
                String qData = ((EditText) findViewById(R.id.newQuestion)).getText().toString();
                String aData = ((EditText) findViewById(R.id.newAnswer)).getText().toString();

                data. putExtra("newQ", qData);
                data.putExtra("newA", aData);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}

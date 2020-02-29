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
                data. putExtra("newQ", getResources().getString(R.string.new_q));
                data.putExtra("newA", getResources().getString(R.string.new_a));
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String newQ = data.getExtras().getString("newQ");
            String newA = data.getExtras().getString("newA");
        }
    }

}

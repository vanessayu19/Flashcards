package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                String qData = ((EditText) findViewById(R.id.newQuestion)).getText().toString();
                String aData = ((EditText) findViewById(R.id.newAnswer)).getText().toString();

                if (TextUtils.isEmpty(qData) || TextUtils.isEmpty((aData))) {
                    Toast message = Toast.makeText(
                            getApplicationContext(), "Must fill in both Question and Answer!",
                            Toast.LENGTH_SHORT);
                    // message.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                    message.show();
                } else {
                    // create data to send back when this activity is closed
                    Intent data = new Intent();

                    data.putExtra("newQ", qData);
                    data.putExtra("newA", aData);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}

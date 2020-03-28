package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                overridePendingTransition(R.anim.right_out, R.anim.left_in);
            }
        });

        // save and close new card
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qData = ((EditText) findViewById(R.id.newQuestion)).getText().toString();
                String aData = ((EditText) findViewById(R.id.newAnswer)).getText().toString();
                String w1Data = ((EditText) findViewById(R.id.wrongAnswer1)).getText().toString();
                String w2Data = ((EditText) findViewById(R.id.wrongAnswer2)).getText().toString();

                if (TextUtils.isEmpty(qData) || TextUtils.isEmpty(aData) ||
                        TextUtils.isEmpty(w1Data) || TextUtils.isEmpty(w2Data)) {
                    Toast message = Toast.makeText(
                            getApplicationContext(), "Must fill in both Question and Answers!",
                            Toast.LENGTH_SHORT);
                    // message.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                    message.show();
                } else {
                    // create data to send back when this activity is closed
                    Intent data = new Intent();

                    data.putExtra("newQ", qData);
                    data.putExtra("newA", aData);
                    data.putExtra("wrong1", w1Data);
                    data.putExtra("wrong2", w2Data);
                    setResult(RESULT_OK, data);
                    finish();
                    overridePendingTransition(R.anim.right_out, R.anim.left_in);
                }
            }
        });
    }
}

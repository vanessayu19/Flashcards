package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditCurrentCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_card);

        // paste current question and answer from intent into EditText
        String currentQ = getIntent().getStringExtra("qText");
        String currentA = getIntent().getStringExtra("aText");

        ((EditText) findViewById(R.id.question)).setText(currentQ);
        ((EditText) findViewById(R.id.answer)).setText(currentA);

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
                // create data to send back when this activity is closed
                Intent intent = new Intent();
                String qData = ((EditText) findViewById(R.id.newQuestion)).getText().toString();
                String aData = ((EditText) findViewById(R.id.newAnswer)).getText().toString();

                // check that both EditText views are populated
                if (TextUtils.isEmpty(qData) || TextUtils.isEmpty(aData)){
                    Toast message = Toast.makeText(getApplicationContext(),
                            "Must answer both Question and Answer!",Toast.LENGTH_SHORT);
                    // default is bottom|center, but the line below does this as well
                    // message.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
                    message.show();
                } else {
                    intent.putExtra("newQ", qData);
                    intent.putExtra("newA", aData);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}

package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditCurrentCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_card);

        // paste current question and answers from intent into EditText
        String currentQ = getIntent().getStringExtra("qText");
        String currentA = getIntent().getStringExtra("aText");
        String currentw1 = getIntent().getStringExtra("w1Text");
        String currentw2 = getIntent().getStringExtra("w2Text");

        ((EditText) findViewById(R.id.question)).setText(currentQ);
        ((EditText) findViewById(R.id.answer)).setText(currentA);
        ((EditText) findViewById(R.id.wrongAnswer1)).setText(currentw1);
        ((EditText) findViewById(R.id.wrongAnswer2)).setText(currentw2);

        // close edit card page
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // dismisses this current activity
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        // save and close new card
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create data to send back when this activity is closed
                String qData = ((EditText) findViewById(R.id.question)).getText().toString();
                String aData = ((EditText) findViewById(R.id.answer)).getText().toString();
                String w1Data = ((EditText) findViewById(R.id.wrongAnswer1)).getText().toString();
                String w2Data = ((EditText) findViewById(R.id.wrongAnswer2)).getText().toString();

                // check that both EditText views are populated
                if (TextUtils.isEmpty(qData) || TextUtils.isEmpty(aData)){
                    Toast message = Toast.makeText(getApplicationContext(),
                            "Must answer both Question and Answer!", Toast.LENGTH_SHORT);
                    // default is bottom|center, but the line below does this as well
                    // message.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
                    message.show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("newQ", qData);
                    intent.putExtra("newA", aData);
                    intent.putExtra("newW1", w1Data);
                    intent.putExtra("newW2", w2Data);
                    setResult(RESULT_OK, intent);
                    finish();
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        });
    }
}

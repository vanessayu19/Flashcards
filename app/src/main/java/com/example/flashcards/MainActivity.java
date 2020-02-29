package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // click question to show answer
        findViewById(R.id.flashcard_question).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("card", "clikcing question");

                    findViewById(R.id.flashcard_answer).setVisibility(view.VISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(view.INVISIBLE);
                }
            }
        );

        // click answer to show question
        findViewById(R.id.flashcard_answer).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("card", "clicking answer");

                    findViewById(R.id.flashcard_answer).setVisibility(view.INVISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(view.VISIBLE);
                }
            }
        );

        // click eye to make answer choices invisible
        findViewById(R.id.visible).setOnClickListener(
            new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    // toggle button
                    findViewById(R.id.invisible).setVisibility(View.VISIBLE);
                    findViewById(R.id.visible).setVisibility(View.INVISIBLE);

                    // make choices invisible
                    findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answer3).setVisibility(View.INVISIBLE);
                }
            }
        );

        // click crossed-out eye to make answer choices visible
        findViewById(R.id.invisible).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // toggle button
                        findViewById(R.id.visible).setVisibility(View.VISIBLE);
                        findViewById(R.id.invisible).setVisibility(View.INVISIBLE);

                        // make choices visible
                        findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                        findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                        findViewById(R.id.answer3).setVisibility(View.VISIBLE);
                    }
                }
        );

        // change color to indicate if answer is wrong or right
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("answers", "clicking answer1");

                String ans = ((TextView) findViewById(R.id.answer1)).getText().toString();
                if (ans.equals(getResources().getString(R.string.card_a))) {
                    // change color to green
                    findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.pastelGreen));
                } else {
                    // change color to red
                    findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.errorRed));
                }
            }
        });

        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("answers", "clicking answer2");

                String ans = ((TextView) findViewById(R.id.answer2)).getText().toString();
                if (ans.equals(getResources().getString(R.string.card_a))) {
                    // change color to green
                    findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.pastelGreen));
                } else {
                    // change color to red
                    findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.errorRed));
                }
            }
        });

        findViewById(R.id.answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("answers", "clicking answer3");

                String ans = ((TextView) findViewById(R.id.answer3)).getText().toString().trim();
                if (ans.equals(getResources().getString(R.string.card_a))) {
                    // change color to green
                    findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.pastelGreen));
                } else {
                    // change color to red
                    findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.errorRed));
                }
            }
        });

        // go to a new page
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });
    };
}

package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
                        findViewById(R.id.flashcard_answer).setVisibility(view.VISIBLE);
                        findViewById(R.id.flashcard_question).setVisibility(view.INVISIBLE);
                    }
                }
        );
    }

}

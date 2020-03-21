package com.example.flashcards;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Flashcard database and a list to hold all the flashcards
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards; // to access all flashcards
    int currentCardDisplayedIndex = 0; // track what card is being displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext()); // initialize flashcardDatabase instance
        allFlashcards = flashcardDatabase.getAllCards(); // access cards

        // check if there are saved flashcards to display
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        // click question to show answer
        findViewById(R.id.flashcard_question).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("card", "clicking question");

                    View answerSideView = findViewById(R.id.flashcard_answer);
                    View questionSideView = findViewById(R.id.flashcard_question);

                    // get the center for the clipping circle
                    int cx = answerSideView.getWidth() / 2;
                    int cy = answerSideView.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                    // hide the question and show the answer to prepare for playing the animation!
                    questionSideView.setVisibility(View.INVISIBLE);
                    answerSideView.setVisibility(View.VISIBLE);

                    anim.setDuration(1500); // how the animation will take
                    anim.start(); // this runs the whole animation
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
                if (ans.equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
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
                if (ans.equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
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
                if (ans.equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
                    // change color to green
                    findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.pastelGreen));
                } else {
                    // change color to red
                    findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.errorRed));
                }
            }
        });

        // create a new card
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,1);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    // enter animation for new activity, exit animation for prev activity
            }
        });

        // edit current card
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditCurrentCardActivity.class);

                // send current question and answer
                String qData = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                intent.putExtra("qText", qData);

                String aData = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();
                intent.putExtra("aText", aData);

                String w1Data = ((TextView) findViewById(R.id.answer2)).getText().toString();
                intent.putExtra("w1Text", w1Data);

                String w2Data = ((TextView) findViewById(R.id.answer3)).getText().toString();
                intent.putExtra("w2Text",w2Data);

                MainActivity.this.startActivityForResult(intent, 2);
                // left in, right out animations
            }
        });

        // go to next card (if possible)
        findViewById(R.id.nextCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);

                        // set text to question and answer of next card
                        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());

                        // reset all answer options
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                // check not out of bounds index
                if (currentCardDisplayedIndex > allFlashcards.size() - 1){
                    Log.d("nextCard", "in the if loop");
                    currentCardDisplayedIndex = 0;
                }

                if (allFlashcards.size() > 0)
                {
                    // display question side
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);

                    findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                }


            }
        });
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String newQ = data.getExtras().getString("newQ");
            ((TextView) findViewById(R.id.flashcard_question)).setText(newQ);

            String newA = data.getExtras().getString("newA");
            ((TextView) findViewById(R.id.flashcard_answer)).setText(newA);
            ((TextView) findViewById(R.id.answer1)).setText(newA);

            String wrong1 = data.getExtras().getString("wrong1");
            ((TextView) findViewById(R.id.answer2)).setText(wrong1);

            String wrong2 = data.getExtras().getString("wrong2");
            ((TextView) findViewById(R.id.answer3)).setText(wrong2);

            // update flashcard database with new card and add to list
            flashcardDatabase.insertCard(new Flashcard(newQ, newA, wrong1, wrong2));
            allFlashcards = flashcardDatabase.getAllCards(); // update the variable of all flashcards

            // display Snackbar notification
            Snackbar.make(findViewById(R.id.flashcard_question), "New card successfully created!",
                    Snackbar.LENGTH_SHORT).show();
        } else if (requestCode == 2 && resultCode == RESULT_OK){
            String newQ = data.getExtras().getString("newQ"); // get data
            ((TextView) findViewById(R.id.flashcard_question)).setText(newQ); // set into current/displayed card
            allFlashcards.get(currentCardDisplayedIndex).setQuestion(newQ); // update database

            String newA = data.getExtras().getString("newA");
            ((TextView) findViewById(R.id.flashcard_answer)).setText(newA);
            ((TextView) findViewById(R.id.answer1)).setText(newA);
            allFlashcards.get(currentCardDisplayedIndex).setAnswer(newA);

            String newWrong1 = data.getExtras().getString("newW1");
            ((TextView) findViewById(R.id.answer2)).setText(newWrong1);
            allFlashcards.get(currentCardDisplayedIndex).setWrongAnswer1(newWrong1);

            String newWrong2 = data.getExtras().getString("newW2");
            ((TextView) findViewById(R.id.answer3)).setText(newWrong2);
            allFlashcards.get(currentCardDisplayedIndex).setWrongAnswer2(newWrong2);

            //update allFlashcards
            //allFlashcards = flashcardDatabase.getAllCards();

            // display Snackbar notification
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully updated!",
                    Snackbar.LENGTH_SHORT).show();
        }
    }
}

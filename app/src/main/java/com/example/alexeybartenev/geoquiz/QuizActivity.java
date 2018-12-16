package com.example.alexeybartenev.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QuizActivity extends AppCompatActivity {

    private Question[] mQuestions = {
            new Question(1,false,0),
            new Question(2,false,0),
            new Question(3,false,0),
            new Question(4,false,0),
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Button true_but = findViewById(R.id.true_button);
        Button false_but = findViewById(R.id.false_button);
        ImageButton cheat_but = findViewById(R.id.cheat_but);
        ImageButton next_but = findViewById(R.id.next_but);
    }
}

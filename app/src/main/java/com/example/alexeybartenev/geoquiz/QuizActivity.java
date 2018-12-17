package com.example.alexeybartenev.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Question[] mQuestions = {
            new Question(R.string.quiestion1,false,true,0),
            new Question(R.string.quiestion2,false,true,0),
            new Question(R.string.quiestion3,true,false,0),
            new Question(R.string.quiestion4,false,false,0)
    };

    boolean check_results = true;
    int false_res = 0;
    int true_res = 0;
    Button true_but;
    Button false_but;
    ImageButton cheat_but;
    TextView question_txt;
    private static final int REQUEST_CODE_CHEAT = 0;


    private int questionId = 0; //ИД отображаемого вопроса
    private boolean mIsCheater;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Q_ID",questionId);
    }

    private void updateQuestion(boolean p_bool) {
        if (mQuestions[questionId].getMflag_answered() == 0) {
            if (mIsCheater) {Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();} else {
                mQuestions[questionId].setAnswer(p_bool);
                mQuestions[questionId].setMflag_answered(1);
            }
        }
    }

    void go_to_next_question() {
        true_res=0;
        false_res=0;
        check_results=true;
        for (Question qq:mQuestions
             ) {
            if (qq.getMflag_answered()==0) check_results=false;
            if (qq.isAnswer() && qq.getMflag_answered()==1) true_res++;
            if (!qq.isAnswer() && qq.getMflag_answered()==1) false_res++;
        }
        if (!check_results) {
            questionId = (questionId + 1) % mQuestions.length;
            mIsCheater = false;
            question_txt.setText(mQuestions[questionId].getMquestionId());
            if (mQuestions[questionId].getMflag_answered() == 1) {
                true_but.setEnabled(false);
                false_but.setEnabled(false);
            } else {
                true_but.setEnabled(true);
                false_but.setEnabled(true);
            }
        } else Toast.makeText(this, "Правда = "+true_res+" Ложь="+false_res, Toast.LENGTH_SHORT).show();
    }


    void go_to_prev_question(){
        if (mQuestions[questionId].getMflag_answered()==0) {
        if (questionId == 0) {
            questionId = mQuestions.length;
        } else {
            questionId += -1;
        }
        question_txt.setText(mQuestions[questionId].getMquestionId());
        } else {
            false_but.setEnabled(false);
            true_but.setEnabled(false);
        }
    }

    /*Вызыывается при возврате результата интента из дочерней активности*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question_txt = findViewById(R.id.questionTextView);
        if (savedInstanceState != null) {
            questionId = savedInstanceState.getInt("Q_ID");
        }
        question_txt.setText(mQuestions[questionId].getMquestionId());
        question_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_to_next_question();
            }
        });
        true_but = findViewById(R.id.true_button);
        true_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(true);
            }
        });
        false_but = findViewById(R.id.false_button);
        false_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(false);
            }
        });
        cheat_but = findViewById(R.id.cheat_but);
        cheat_but.setImageResource(android.R.drawable.ic_menu_help);
        cheat_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                start Activity
                boolean answerIsTrue = mQuestions[questionId].isRealAnswer();
                Intent intent = CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
//                startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });
        ImageButton next_but = findViewById(R.id.next_but);
        next_but.setImageResource(android.R.drawable.ic_media_next);
        next_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_to_next_question();
            }
        });
    }
}

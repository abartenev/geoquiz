package com.example.alexeybartenev.geoquiz;

public class Question {
    private int mquestionId;
    private boolean mAnswer;
    private int mflag_answered;

    public Question(int mquestionId, boolean answer, int mflag_answered) {
        this.mquestionId = mquestionId;
        mAnswer = answer;
        this.mflag_answered = mflag_answered;
    }

    public int getMquestionId() {
        return mquestionId;
    }

    public void setMquestionId(int mquestionId) {
        this.mquestionId = mquestionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public int getMflag_answered() {
        return mflag_answered;
    }

    public void setMflag_answered(int mflag_answered) {
        this.mflag_answered = mflag_answered;
    }
}

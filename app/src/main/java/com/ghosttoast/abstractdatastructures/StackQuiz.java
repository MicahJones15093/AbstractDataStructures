package com.ghosttoast.abstractdatastructures;

// TODO: make template?
public class StackQuiz {
    private int question;           // asks user to add dish to pile or clean dish from pile
    private StackChoice answer;     // either push or pop
    private String letter;          // letter to be shown on dish

    public StackQuiz(int question, StackChoice answer, String letter){
        this.question = question;
        this.answer = answer;
        this.letter = letter;
    }

    public int getQuestion(){
        return this.question;
    }

    public void setQuestion(int question){
        this.question = question;
    }

    public StackChoice getAnswer(){
        return this.answer;
    }

    public void setAnswer(StackChoice answer){
        this.answer = answer;
    }

    public String getLetter() { return letter; }

    public void setLetter(String letter) { this.letter = letter; }
}


package com.ghosttoast.abstractdatastructures;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class stack extends AppCompatActivity {
    private ImageButton pushButton;
    private ImageButton popButton;

    private ImageButton helpButton;

    private Button dishBtn1;
    private Button dishBtn2;
    private Button dishBtn3;
    private Button dishBtn4;
    private Button dishBtn5;

    private Button currButton;

    private TextView qTextView;

    private List<StackQuiz> quiz = new ArrayList<>();
    private Stack<StackQuiz> stack = new Stack<>();
    private StackQuiz question;

    private List<Button> buttonList = new ArrayList<>();

    // used to iterate through questions
    private Iterator<StackQuiz> quizIterator;

    private int dishCount = 0;
    private int currQuestion=0;

    private RelativeLayout popupWindowLayout;
    private PopupWindow popupWindow;
    private boolean popupIsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        createQuestions();

        pushButton = (ImageButton) findViewById(R.id.pushButton);
        popButton = (ImageButton) findViewById(R.id.popButton);

        dishBtn1 = (Button) findViewById(R.id.dishBtn1);
        dishBtn2 = (Button) findViewById(R.id.dishBtn2);
        dishBtn3 = (Button) findViewById(R.id.dishBtn3);
        dishBtn4 = (Button) findViewById(R.id.dishBtn4);
        dishBtn5 = (Button) findViewById(R.id.dishBtn5);
        createButtonList();

        helpButton = (ImageButton) findViewById(R.id.helpBtn);

        qTextView = (TextView) findViewById(R.id.questionTV);
        quizIterator = quiz.iterator();
        question = quizIterator.next();
        qTextView.setText(question.getQuestion());


        dishBtn1.setOnClickListener(e->
        {
            if(stack.size() == 5)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dishBtn2.setOnClickListener(e->
        {
            if(stack.size() == 4)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dishBtn3.setOnClickListener(e->
        {
            if(stack.size() == 3)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dishBtn4.setOnClickListener(e->
        {
            if(stack.size() == 2)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dishBtn5.setOnClickListener(e->
        {
            if(stack.size() == 1)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        pushButton.setOnClickListener(e->
        {
            // temporarily disable button
            setBtnUsability(false);
            if(question.getAnswer() == StackChoice.PUSH)
            {
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();
                stack.push(question);
                updateStack();
                displayNextQuestion();
            }
            else
            {
                Toast.makeText(stack.this,
                        "We need to wash and remove a dish from the pile, not add one.",
                        Toast.LENGTH_SHORT)
                        .show();
                setBtnUsability(true);
            }
        });

        popButton.setOnClickListener(e->
        {
            setBtnUsability(false);
            if(question.getAnswer() == StackChoice.POP)
            {
                dishCount--;
                Toast.makeText(stack.this,
                        "Awesome!",
                        Toast.LENGTH_SHORT)
                        .show();

                //currButton = buttonList.get(dishCount);

                //if(dishCount%2 == 0) {currButton.setBackgroundResource(R.drawable.mint);}
                //else {currButton.setBackgroundResource(R.drawable.yellow);}

                //currButton.setText("");
                stack.pop();
                updateStack();
                displayNextQuestion();
            }
            else
            {
                Toast.makeText(stack.this,
                        "Not quite. Try again!",
                        Toast.LENGTH_SHORT)
                        .show();
                setBtnUsability(true);
            }
        });

        popupWindowLayout = (RelativeLayout) findViewById(R.id.popupWindowLayout);
        helpButton.setOnClickListener(v-> {
            if(!popupIsOpen)
            {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_window_stack, null);

                popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
            else
            {
                popupWindow.dismiss();
            }
            popupIsOpen = !popupIsOpen;
            /*LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.popup_window, null);

            popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            customView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });*/
        });


        //initialize onLongClickListeners
        View.OnLongClickListener longClickListener = new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    v.startDragAndDrop(data, myShadowBuilder, v, 0);
                } else {
                    v.startDrag(data, myShadowBuilder, v, 0);
                }
                return true;
            }
        };

        dishBtn1.setOnLongClickListener(longClickListener);
        dishBtn2.setOnLongClickListener(longClickListener);
        dishBtn3.setOnLongClickListener(longClickListener);
        dishBtn4.setOnLongClickListener(longClickListener);
        dishBtn5.setOnLongClickListener(longClickListener);


        //Initialize onDragListeners
        View.OnDragListener dragListener = new View.OnDragListener(){

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                switch (dragEvent){
                    case DragEvent.ACTION_DRAG_ENTERED:
                        final View view = (View) event.getLocalState(); //which view is dragged?

                        if(question.getAnswer() == StackChoice.POP)
                        {
                            if (view.getId() == R.id.dishBtn5) {
                                if (stack.size() == 1) {
                                    Toast.makeText(stack.this,
                                            "Awesome!",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    stack.pop();
                                    updateStack();
                                    displayNextQuestion();
                                } else {
                                    Toast.makeText(stack.this,
                                            "There is another dish that should be washed first.",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                            if (view.getId() == R.id.dishBtn4) {
                                if (stack.size() == 2) {
                                    Toast.makeText(stack.this,
                                            "Awesome!",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    stack.pop();
                                    updateStack();
                                    displayNextQuestion();
                                } else {
                                    Toast.makeText(stack.this,
                                            "There is another dish that should be washed first.",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(stack.this,
                                    "We need to add a dish to the pile. No need to wash.",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        break;
                }
                return true;
            }
        };
        popButton.setOnDragListener(dragListener);


    }

    private void displayNextQuestion() {
        //question = quizIterator.next();
        //qTextView.setText(question.getQuestion());

        if (quizIterator.hasNext()){
            question = quizIterator.next();
            qTextView.setText(question.getQuestion());
            setBtnUsability(true);
        }
        else
        {
            setBtnUsability(false);
            qTextView.setText("Great job! You're a stack star!");
        }

    }

    private void createQuestions() {
        quiz.add(new StackQuiz(R.string.Q1, StackChoice.PUSH, "A"));
        quiz.add(new StackQuiz(R.string.Q2, StackChoice.POP, "*"));
        quiz.add(new StackQuiz(R.string.Q3, StackChoice.PUSH, "I"));
        quiz.add(new StackQuiz(R.string.Q4, StackChoice.PUSH, "C"));
        quiz.add(new StackQuiz(R.string.Q5, StackChoice.POP, "*"));
        quiz.add(new StackQuiz(R.string.Q6, StackChoice.PUSH, "H"));
    }

    private void createButtonList() {
        buttonList.add(dishBtn1);
        buttonList.add(dishBtn2);
        buttonList.add(dishBtn3);
        buttonList.add(dishBtn4);
        buttonList.add(dishBtn5);
    }

    private void setBtnUsability(boolean enable){
        // if enable is set to true, enable buttons. Otherwise, disable.
        pushButton.setEnabled(enable);
        popButton.setEnabled(enable);
    }

    private void setDishBtnUsability(boolean enable){
        // if enable is set to true, enable buttons. Otherwise, disable.
        dishBtn1.setEnabled(enable);
        dishBtn2.setEnabled(enable);
        dishBtn3.setEnabled(enable);
        dishBtn4.setEnabled(enable);
        dishBtn5.setEnabled(enable);
    }

    private void updateStack(){
        for(int i=0; i<stack.size(); i++)
        {
            currButton = buttonList.get(4-i);
            currButton.setBackgroundResource(R.drawable.plate);
            String text = Integer.toString(stack.size());
            currButton.setText(stack.get(i).getLetter());
        }

        for(int i=0; i<(5-stack.size()); i++)
        {
            currButton = buttonList.get(i);
            if(i%2 == 0) {currButton.setBackgroundResource(R.drawable.mint);}
            else {currButton.setBackgroundResource(R.drawable.yellow);}
            currButton.setText("");
        }

        //for(int i = 4; i<=(5-st))
    }
}

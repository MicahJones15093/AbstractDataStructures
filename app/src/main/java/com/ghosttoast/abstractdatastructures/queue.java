package com.ghosttoast.abstractdatastructures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.media.Image;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class queue extends AppCompatActivity {
    //declaring UI components
    private ImageButton dequeueLine;
    private ImageButton student1;
    private ImageButton student2;
    private ImageButton student3;
    private ImageButton helpButton;

    private TextView line0;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;

    private TextView prompt;
    private TextView command;
    private TextView helpWindow;

    // instantiate queues
    public Queue<Integer> BusLine = new LinkedList<>();
    public Queue<Integer> Matthew = new LinkedList<>();
    public Queue<Integer> Becca = new LinkedList<>();
    public Queue<Integer> James = new LinkedList<>();

    private Handler mHandler = new Handler();

    public Boolean startDequeue = false;
    private int nCounter = 0;

    private RelativeLayout popupWindowLayout;
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        //initialize UI components
        helpButton = (ImageButton) findViewById(R.id.helpButton);

        dequeueLine = (ImageButton) findViewById(R.id.dequeueButton);
        student1 = (ImageButton) findViewById(R.id.student1);
        student2 = (ImageButton) findViewById(R.id.student2);
        student3 = (ImageButton) findViewById(R.id.student3);


        line0 = (TextView) findViewById(R.id.i0);
        line1 = (TextView) findViewById(R.id.i1);
        line2 = (TextView) findViewById(R.id.i2);
        line3 = (TextView) findViewById(R.id.i3);
        line4 = (TextView) findViewById(R.id.i4);

        prompt = (TextView) findViewById(R.id.prompt);
        command = (TextView) findViewById(R.id.commandView);
        helpWindow = (TextView) findViewById(R.id.helpWindow);

        //initialize help button with popup window
        popupWindowLayout = (RelativeLayout) findViewById(R.id.popupWindowLayout);

        // help button
        helpButton.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.qpopup_window, null);

            popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            customView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        });


        student1.setOnClickListener(v -> {
            if (BusLine.size() < 5) {
                Drawable boy = ResourcesCompat.getDrawable(getResources(), R.drawable.boy, null);
                boy.setBounds(0, 0, line4.getMeasuredWidth(), line4.getMeasuredHeight() - 2);

                if (BusLine.isEmpty()) {
                    line4.setCompoundDrawables(null, boy, null, null);
                    line4.setText("1");
                    BusLine.add(1);
                    command.setText("enqueuing Matthew first");
                    mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second

                }
                else {
                    switch (BusLine.size()) {
                        case 1:
                            line3.setCompoundDrawables(null, boy, null, null);
                            line3.setText("1");
                            BusLine.add(1);
                            command.setText("enqueuing Matthew second");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, boy, null, null);
                            line2.setText("1");
                            BusLine.add(1);
                            command.setText("enqueuing Matthew third");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 3:
                            line1.setCompoundDrawables(null, boy, null, null);
                            line1.setText("1");
                            BusLine.add(1);
                            command.setText("enqueuing Matthew fourth");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 4:
                            line0.setCompoundDrawables(null, boy, null, null);
                            line0.setText("1");
                            BusLine.add(1);
                            command.setText("enqueuing Matthew last");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }
        });


        student2.setOnClickListener(v -> {
            if (BusLine.size() < 5) {
                Drawable girl = ResourcesCompat.getDrawable(getResources(), R.drawable.girl, null);
                girl.setBounds(0, 0, line4.getMeasuredWidth(), line4.getMeasuredHeight());
                if (BusLine.isEmpty()) {
                    line4.setCompoundDrawables(null, girl, null, null);
                    line4.setText("2");
                    command.setText("enqueuing Becca first");
                    mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                    BusLine.add(2);
                } else {
                    switch (BusLine.size()) {
                        case 1:
                            line3.setCompoundDrawables(null, girl, null, null);
                            line3.setText("2");
                            BusLine.add(2);
                            command.setText("enqueuing Becca second");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, girl, null, null);
                            line2.setText("2");
                            BusLine.add(2);
                            command.setText("enqueuing Becca third");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 3:
                            line1.setCompoundDrawables(null, girl, null, null);
                            line1.setText("2");
                            BusLine.add(2);
                            command.setText("enqueuing Becca fourth");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 4:
                            line0.setCompoundDrawables(null, girl, null, null);
                            line0.setText("2");
                            BusLine.add(2);
                            command.setText("enqueuing Becca last");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }

        });


        student3.setOnClickListener(v -> {
            if (BusLine.size() < 5) {
                Drawable boy2 = ResourcesCompat.getDrawable(getResources(), R.drawable.boy2, null);
                boy2.setBounds(0, 0, line4.getMeasuredWidth(), line4.getMeasuredHeight());

                if (BusLine.isEmpty())
                {
                    line4.setCompoundDrawables(null, boy2, null, null);
                    line4.setText("3");
                    command.setText("enqueuing James first");
                    mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                    BusLine.add(3);
                }

                else {
                    switch (BusLine.size()) {
                        case 1:
                            line3.setCompoundDrawables(null, boy2, null, null);
                            line3.setText("3");
                            BusLine.add(3);
                            command.setText("enqueuing James second");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, boy2, null, null);
                            line2.setText("3");
                            BusLine.add(3);
                            command.setText("enqueuing James third");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 3:
                            line1.setCompoundDrawables(null, boy2, null, null);
                            line1.setText("3");
                            BusLine.add(3);
                            command.setText("enqueuing James fourth");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 1 second
                            break;
                        case 4:
                            line0.setCompoundDrawables(null, boy2, null, null);
                            line0.setText("3");
                            BusLine.add(3);
                            command.setText("enqueuing James last");
                            mHandler.postDelayed(hMyTimeTask, 3000); // delay 6 seconds
                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }

        });


        //initialize onLongClickListeners
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, myShadowBuilder, v, 0);
                } else {
                    v.startDrag(data, myShadowBuilder, v, 0);
                }
                return true;
            }
        };

        line0.setOnLongClickListener(longClickListener);
        line1.setOnLongClickListener(longClickListener);
        line2.setOnLongClickListener(longClickListener);
        line3.setOnLongClickListener(longClickListener);
        line4.setOnLongClickListener(longClickListener);


        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                switch (dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        final View view = (View) event.getLocalState(); //which view is dragged?

                        if (view.getId() == R.id.i0) {
                            if (BusLine.size() == 1)
                            {
                                line0.setCompoundDrawables(null, null, null, null);
                                line0.setText("");
                                BusLine.poll();
                                line0.setVisibility(View.INVISIBLE);
                                command.setText("dequeue()");
                                updateBusLine();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "There is a student who was in line first!", Toast.LENGTH_SHORT).show();

                        } else if (view.getId() == R.id.i1)
                        {
                            if (BusLine.size() == 2)
                            {
                                line1.setCompoundDrawables(null, null, null, null);
                                line1.setText("");
                                BusLine.poll();
                                line1.setVisibility(View.INVISIBLE);
                                command.setText("dequeue()");
                                updateBusLine();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "There is a student who was in line first!", Toast.LENGTH_SHORT).show();
                        }

                        else if (view.getId() == R.id.i2)
                        {
                            if (BusLine.size() == 3)
                            {
                                line2.setCompoundDrawables(null, null, null, null);
                                line2.setText("");
                                BusLine.poll();
                                line2.setVisibility(View.INVISIBLE);
                                command.setText("dequeue()");
                                updateBusLine();
                            }

                            else
                                Toast.makeText(getApplicationContext(), "There is a student who was in line first!", Toast.LENGTH_SHORT).show();
                        }

                        else if (view.getId() == R.id.i3)
                        {
                            if (BusLine.size() == 4)
                            {
                                line3.setCompoundDrawables(null, null, null, null);
                                line3.setText("");
                                BusLine.poll();
                                line3.setVisibility(View.INVISIBLE);
                                command.setText("dequeue()");
                                updateBusLine();

                            }
                            else
                                Toast.makeText(getApplicationContext(), "There is a student who was in line first!", Toast.LENGTH_SHORT).show();
                        }
                        else if (view.getId() == R.id.i4) {
                            if (BusLine.size() == 5) {
                                line4.setCompoundDrawables(null, null, null, null);
                                line4.setText("");
                                line4.setVisibility(View.INVISIBLE);
                                BusLine.poll();
                                command.setText("dequeue()");
                                updateBusLine();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "There is a student who was in line first!", Toast.LENGTH_SHORT).show();
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

        dequeueLine.setOnDragListener(dragListener);
    }

    //setFlag determines whether the user has started the game or is currently in the game already
    public void setFlag(boolean flag) {
        if (startDequeue) {
            //user is already playing game
        } else if (flag) {
            //user has started the game
            startDequeue = true;
            prompt.setText(R.string.queuePrompt2);
        }
    }

    private Runnable hMyTimeTask = new Runnable() {
        public void run() {
            nCounter++;
            command.setText(" ");
        }
    };

    public void updateBusLine() {

        //check if hospitalLine queue is empty, if so, game is complete!
        if (BusLine.isEmpty()) {
            command.setText("Congratulations!");
            prompt.setText(R.string.priorityPrompt3);
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line0.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);
            startDequeue = false;
            return;
        }

        Drawable sad = ResourcesCompat.getDrawable(getResources(), R.drawable.boy2, null);
        sad.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
        Drawable sick = ResourcesCompat.getDrawable(getResources(), R.drawable.girl, null);
        sick.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
        Drawable zombie = ResourcesCompat.getDrawable(getResources(), R.drawable.boy, null);
        zombie.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
    }
}


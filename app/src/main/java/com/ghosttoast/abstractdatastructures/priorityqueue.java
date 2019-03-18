package com.ghosttoast.abstractdatastructures;

import android.app.ActionBar;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
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

import java.util.PriorityQueue;

public class priorityqueue extends AppCompatActivity {

    //declaring UI components
    private ImageButton dequeueLine;
    private ImageButton priority3;
    private ImageButton priority2;
    private ImageButton priority1;
    private ImageButton helpButton;

    private TextView line0;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;

    private TextView prompt;
    private TextView command;
    private TextView helpWindow;

    //other declarations

    //keeps track of the line and what priority values it contains
    public PriorityQueue<Integer> hospitalLine = new PriorityQueue<>();

    //keeps track of the index values of each priority
    public PriorityQueue<Integer> sickly = new PriorityQueue<>();
    public PriorityQueue<Integer> sicker = new PriorityQueue<>();
    public PriorityQueue<Integer> sickest = new PriorityQueue<>();

    public Boolean startDequeue = false;

    private RelativeLayout popupWindowLayout;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priorityqueue);

    //initialize UI components
        dequeueLine = (ImageButton) findViewById(R.id.dequeueButton);
        priority3 = (ImageButton) findViewById(R.id.priority3);
        priority2 = (ImageButton) findViewById(R.id.priority2);
        priority1 = (ImageButton) findViewById(R.id.priority1);
        helpButton = (ImageButton) findViewById(R.id.helpButton);

        line0 = (TextView) findViewById(R.id.index0);
        line1 = (TextView) findViewById(R.id.index1);
        line2 = (TextView) findViewById(R.id.index2);
        line3 = (TextView) findViewById(R.id.index3);
        line4 = (TextView) findViewById(R.id.index4);

        prompt = (TextView) findViewById(R.id.prompt);
        command = (TextView) findViewById(R.id.commandView);
        helpWindow = (TextView) findViewById(R.id.helpWindow);

//initialize help button with popup window
        popupWindowLayout = (RelativeLayout) findViewById(R.id.popupWindowLayout);

        helpButton.setOnClickListener(v-> {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.popup_window, null);

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

    //initialize onClickListeners
        priority1.setOnClickListener(v->{
            if(hospitalLine.size() < 5) {
                Drawable zombie = ResourcesCompat.getDrawable(getResources(), R.drawable.zombie, null);
                zombie.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());

                //the if/else block adds the sickest patient into the next spot in line, adds the index of the
                //current line index to the sickest queue, and adds the priority value to the hospitalLine queue
                if (hospitalLine.isEmpty()) {
                    line0.setCompoundDrawables(null, zombie, null, null);
                    line0.setText("1");
                    sickest.add(0);
                    hospitalLine.add(1);
                } else {
                    switch (hospitalLine.size()) {
                        case 1:
                            line1.setCompoundDrawables(null, zombie, null, null);
                            line1.setText("1");
                            hospitalLine.add(1);
                            sickest.add(1);
                            command.setText("enqueue(SICKEST)");
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, zombie, null, null);
                            line2.setText("1");
                            hospitalLine.add(1);
                            sickest.add(2);
                            command.setText("enqueue(SICKEST)");
                            break;
                        case 3:
                            line3.setCompoundDrawables(null, zombie, null, null);
                            line3.setText("1");
                            hospitalLine.add(1);
                            sickest.add(3);
                            command.setText("enqueue(SICKEST)");
                            break;
                        case 4:
                            line4.setCompoundDrawables(null, zombie, null, null);
                            line4.setText("1");
                            hospitalLine.add(1);
                            sickest.add(4);
                            command.setText("enqueue(SICKEST)");

                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }
        });

        //the if/else block adds the sicker patient into the next spot in line, adds the index of the
        //current line index to the sicker queue, and adds the priority value to the hospitalLine queue
        priority2.setOnClickListener(v->{
            if(hospitalLine.size() < 5) {
                Drawable sick = ResourcesCompat.getDrawable(getResources(), R.drawable.sick, null);
                sick.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
                if (hospitalLine.isEmpty()) {
                    line0.setCompoundDrawables(null, sick, null, null);
                    line0.setText("2");
                    sicker.add(0);
                    hospitalLine.add(2);
                } else {
                    switch (hospitalLine.size()) {
                        case 1:
                            line1.setCompoundDrawables(null, sick, null, null);
                            line1.setText("2");
                            hospitalLine.add(2);
                            sicker.add(1);
                            command.setText("enqueue(SICKER)");
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, sick, null, null);
                            line2.setText("2");
                            hospitalLine.add(2);
                            sicker.add(2);
                            command.setText("enqueue(SICKER)");
                            break;
                        case 3:
                            line3.setCompoundDrawables(null, sick, null, null);
                            line3.setText("2");
                            hospitalLine.add(2);
                            sicker.add(3);
                            command.setText("enqueue(SICKER)");
                            break;
                        case 4:
                            line4.setCompoundDrawables(null, sick, null, null);
                            line4.setText("2");
                            hospitalLine.add(2);
                            sicker.add(4);
                            command.setText("enqueue(SICKER)");

                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }
        });

        //the if/else block adds the sick patient into the next spot in line, adds the index of the
        //current line index to the sick queue, and adds the priority value to the hospitalLine queue
        priority3.setOnClickListener(v->{
            if(hospitalLine.size() < 5) {
                Drawable sad = ResourcesCompat.getDrawable(getResources(), R.drawable.sad, null);
                sad.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
                if (hospitalLine.isEmpty()) {
                    line0.setCompoundDrawables(null, sad, null, null);
                    line0.setText("3");
                    sickly.add(0);
                    hospitalLine.add(3);
                } else {
                    switch (hospitalLine.size()) {
                        case 1:
                            line1.setCompoundDrawables(null, sad, null, null);
                            line1.setText("3");
                            hospitalLine.add(3);
                            sickly.add(1);
                            command.setText("enqueue(SICK)");
                            break;
                        case 2:
                            line2.setCompoundDrawables(null, sad, null, null);
                            line2.setText("3");
                            hospitalLine.add(3);
                            sickly.add(2);
                            command.setText("enqueue(SICK)");
                            break;
                        case 3:
                            line3.setCompoundDrawables(null, sad, null, null);
                            line3.setText("3");
                            hospitalLine.add(3);
                            sickly.add(3);
                            command.setText("enqueue(SICK)");
                            break;
                        case 4:
                            line4.setCompoundDrawables(null, sad, null, null);
                            line4.setText("3");
                            hospitalLine.add(3);
                            sickly.add(4);
                            command.setText("enqueue(SICK)");

                            setFlag(true);

                            break;
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "The line is full!", Toast.LENGTH_SHORT).show();
            }
        });

        //initialize onLongClickListeners with drag and drop functionality
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

        line0.setOnLongClickListener(longClickListener);
        line1.setOnLongClickListener(longClickListener);
        line2.setOnLongClickListener(longClickListener);
        line3.setOnLongClickListener(longClickListener);
        line4.setOnLongClickListener(longClickListener);

        //Initialize onDragListeners
        View.OnDragListener dragListener = new View.OnDragListener(){

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                switch (dragEvent){
                    case DragEvent.ACTION_DRAG_ENTERED:
                        final View view = (View) event.getLocalState(); //which view is dragged?

                        if (view.getId() == R.id.index0){
                            if(checkMove(line0)){ //if move is correct, continue
                                int priority = Integer.parseInt(line0.getText().toString());
                                if (priority == 3){ //dequeue from the sick, sicker, or sickest queue depending on the priority of current index
                                    sickly.poll();
                                } else if (priority == 2){
                                    sicker.poll();
                                } else if (priority == 1){
                                    sickest.poll();
                                }

                                //remove the patient from the line by setting the index to empty
                                line0.setCompoundDrawables(null, null, null, null);
                                line0.setText("");
                                hospitalLine.poll();
                                command.setText("dequeue()");

                                updateHospitalLine(0);
                            }
                        } else if (view.getId() == R.id.index1) {
                            if(checkMove(line1)){ //if move is correct, continue
                                int priority = Integer.parseInt(line1.getText().toString());
                                if (priority == 3){ //dequeue from the sick, sicker, or sickest queue depending on the priority of current index
                                    sickly.poll();
                                } else if (priority == 2){
                                    sicker.poll();
                                } else if (priority == 1){
                                    sickest.poll();
                                }
                                //remove the patient from the line by setting the index to empty
                                line1.setCompoundDrawables(null, null, null, null);
                                line1.setText("");
                                hospitalLine.poll();
                                command.setText("dequeue()");
                                updateHospitalLine(1);
                            }
                        } else if (view.getId() == R.id.index2) {
                            if(checkMove(line2)){ //if move is correct, continue
                                int priority = Integer.parseInt(line2.getText().toString());
                                if (priority == 3){ //dequeue from the sick, sicker, or sickest queue depending on the priority of current index
                                    sickly.poll();
                                } else if (priority == 2){
                                    sicker.poll();
                                } else if (priority == 1){
                                    sickest.poll();
                                }
                                //remove the patient from the line by setting the index to empty
                                line2.setCompoundDrawables(null, null, null, null);
                                line2.setText("");
                                hospitalLine.poll();
                                command.setText("dequeue()");
                                updateHospitalLine(2);
                            }
                        } else if (view.getId() == R.id.index3) {
                            if(checkMove(line3)){ //if move is correct, continue
                                int priority = Integer.parseInt(line3.getText().toString());
                                if (priority == 3){ //dequeue from the sick, sicker, or sickest queue depending on the priority of current index
                                    sickly.poll();
                                } else if (priority == 2){
                                    sicker.poll();
                                } else if (priority == 1){
                                    sickest.poll();
                                }
                                //remove the patient from the line by setting the index to empty
                                line3.setCompoundDrawables(null, null, null, null);
                                line3.setText("");
                                hospitalLine.poll();
                                command.setText("dequeue()");
                                updateHospitalLine(3);
                            }
                        } else if (view.getId() == R.id.index4) {
                            if(checkMove(line4)){ //if move is correct, continue
                                int priority = Integer.parseInt(line4.getText().toString());
                                if (priority == 3){ //dequeue from the sick, sicker, or sickest queue depending on the priority of current index
                                    sickly.poll();
                                } else if (priority == 2){
                                    sicker.poll();
                                } else if (priority == 1){
                                    sickest.poll();
                                }
                                //remove the patient from the line by setting the index to empty
                                line4.setCompoundDrawables(null, null, null, null);
                                line4.setText("");
                                hospitalLine.poll();
                                command.setText("dequeue()");
                            }
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
    public void setFlag(boolean flag){
        if(startDequeue){
            //user is already playing game
        } else if (flag){
            //user has started the game
            startDequeue = true;
            prompt.setText(R.string.priorityPrompt2);
        }
    }

    //checks if the user's choice is correct
    public Boolean checkMove(TextView index){
        int priority = Integer.parseInt(index.getText().toString());
        int first = 0;

        //check for the correct priority value and if it matches the picked patient
        if(priority == hospitalLine.peek()){
            //if priority value is correct, check for the correct index (who was in line first?)
            if (priority == 3){
                first = sickly.peek();
            } else if (priority == 2){
                first = sicker.peek();
            } else if (priority == 1){
                first = sickest.peek();
            }

            //check if picked patient is the first in line
            if((index == line0) && (first == 0)){
                return true;
            } else if ((index == line1) && (first == 1)){
                return true;
            } else if ((index == line2) && (first == 2)){
                return true;
            } else if ((index == line3) && (first == 3)){
                return true;
            } else if ((index == line4) && (first == 4)){
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "There is a patient who was in line first with the same priority!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "There is a patient with a higher priority!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //updates line when an element is removed
    public void updateHospitalLine(int textViewIndex) {

        //check if hospitalLine queue is empty, if so, game is complete!
        if(hospitalLine.isEmpty()){
            command.setText("Congratulations!");
            prompt.setText(R.string.priorityPrompt3);
            startDequeue = false;
            return;
        }

        //get drawable resources of patients
        Drawable sad = ResourcesCompat.getDrawable(getResources(), R.drawable.sad, null);
        sad.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
        Drawable sick = ResourcesCompat.getDrawable(getResources(), R.drawable.sick, null);
        sick.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());
        Drawable zombie = ResourcesCompat.getDrawable(getResources(), R.drawable.zombie, null);
        zombie.setBounds(0, 0, line0.getMeasuredHeight(), line0.getMeasuredHeight());

        switch(textViewIndex){

            //updates the index that was removed from the queue with the next value in line
            //updates all indices after due to no breaks in switch statement
            case 0:
                if (line1.getText() == "3"){
                    line0.setCompoundDrawables(null, sad, null, null);
                    line1.setCompoundDrawables(null, null, null, null);
                    line0.setText("3");
                    line1.setText("");
                    sickly.remove(1);
                    sickly.add(0);

                } else if (line1.getText() == "2"){
                    line0.setCompoundDrawables(null, sick, null, null);
                    line1.setCompoundDrawables(null, null, null, null);
                    line0.setText("2");
                    line1.setText("");
                    sicker.remove(1);
                    sicker.add(0);
                } else if (line1.getText() == "1") {
                    line0.setCompoundDrawables(null, zombie, null, null);
                    line1.setCompoundDrawables(null, null, null, null);
                    line0.setText("1");
                    line1.setText("");
                    sickest.remove(1);
                    sickest.add(0);
                }
            case 1:
                if (line2.getText() == "3"){
                    line1.setCompoundDrawables(null, sad, null, null);
                    line2.setCompoundDrawables(null, null, null, null);
                    line1.setText("3");
                    line2.setText("");
                    sickly.remove(2);
                    sickly.add(1);
                } else if (line2.getText() == "2"){
                    line1.setCompoundDrawables(null, sick, null, null);
                    line2.setCompoundDrawables(null, null, null, null);
                    line1.setText("2");
                    line2.setText("");
                    sicker.remove(2);
                    sicker.add(1);
                } else if (line2.getText() == "1") {
                    line1.setCompoundDrawables(null, zombie, null, null);
                    line2.setCompoundDrawables(null, null, null, null);
                    line1.setText("1");
                    line2.setText("");
                    sickest.remove(2);
                    sickest.add(1);
                }
            case 2:
                if (line3.getText() == "3"){
                    line2.setCompoundDrawables(null, sad, null, null);
                    line3.setCompoundDrawables(null, null, null, null);
                    line2.setText("3");
                    line3.setText("");
                    sickly.remove(3);
                    sickly.add(2);
                } else if (line3.getText() == "2"){
                    line2.setCompoundDrawables(null, sick, null, null);
                    line3.setCompoundDrawables(null, null, null, null);
                    line2.setText("2");
                    line3.setText("");
                    sicker.remove(3);
                    sicker.add(2);
                } else if (line3.getText() == "1") {
                    line2.setCompoundDrawables(null, zombie, null, null);
                    line3.setCompoundDrawables(null, null, null, null);
                    line2.setText("1");
                    line3.setText("");
                    sickest.remove(3);
                    sickest.add(2);
                }
            case 3:
                if (line4.getText() == "3"){
                    line3.setCompoundDrawables(null, sad, null, null);
                    line4.setCompoundDrawables(null, null, null, null);
                    line3.setText("3");
                    line4.setText("");
                    sickly.remove(4);
                    sickly.add(3);
                } else if (line4.getText() == "2"){
                    line3.setCompoundDrawables(null, sick, null, null);
                    line4.setCompoundDrawables(null, null, null, null);
                    line3.setText("2");
                    line4.setText("");
                    sicker.remove(4);
                    sicker.add(3);
                } else if (line4.getText() == "1") {
                    line3.setCompoundDrawables(null, zombie, null, null);
                    line4.setCompoundDrawables(null, null, null, null);
                    line3.setText("1");
                    line4.setText("");
                    sickest.remove(4);
                    sickest.add(3);
                }
        }

    }

}


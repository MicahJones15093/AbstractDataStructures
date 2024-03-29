package com.ghosttoast.abstractdatastructures;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button stack;
    private Button queue;
    private Button priorityqueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stack = (Button) findViewById(R.id.stackButton);
        queue = (Button) findViewById(R.id.queueButton);
        priorityqueue = (Button) findViewById(R.id.priorityQueueButton);

        stack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStackActivity();
            }
        });

        queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQueueActivity();
            }
        });

        priorityqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPriorityQueueActivity();
            }
        });
    }

    private void openStackActivity() {
        Intent intentStack = new Intent(this, stack.class);
        startActivity(intentStack);
    }

    private void openQueueActivity() {
        Intent intentQueue = new Intent(this, queue.class);
        startActivity(intentQueue);
    }

    private void openPriorityQueueActivity() {
        Intent intentPriorityQueue = new Intent (this, priorityqueue.class);
        startActivity(intentPriorityQueue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

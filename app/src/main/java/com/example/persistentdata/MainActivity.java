package com.example.persistentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNTER = "COUNTER";
    int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int defaultCounter = 0;
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        mCounter = settings.getInt(KEY_COUNTER,defaultCounter);
        ((TextView) findViewById(R.id.textView)).setText("Counter: " + Integer.toString(mCounter) + "\n");
    }

    public void onClickCounter(View view){
        mCounter++;
        ((EditText) findViewById(R.id.editText)).setText("Counter: " + Integer.toString(mCounter) + "\n");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER,mCounter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            mCounter = savedInstanceState.getInt(KEY_COUNTER);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, mCounter);
        editor.commit();
    }
}

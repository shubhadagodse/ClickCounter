package com.example.clickcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class MyApplicationClass extends AppCompatActivity {
    private static final MyApplicationClass ourInstance = new MyApplicationClass();

    static MyApplicationClass getInstance() {
        return ourInstance;
    }

    public MyApplicationClass() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Put your application initialization code here.
    }
}

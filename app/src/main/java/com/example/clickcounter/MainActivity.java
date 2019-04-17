package com.example.clickcounter;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import static com.example.clickcounter.R.id.a_main_tv_btnCount;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private int lastOrientation;
    public static final String KEY_LAST_ORIENTATION = "last_orientation";

    private TextView tvBtnCount;
    private TextView tvBgCount;
    private Button clickButton;
    private int btnCounter = 0;
    private int bgCounter = 0;
    protected static final String TAG = MainActivity.class.getName();

    public static final String SHARED_PREFS = "shared preferences";
    public static final String SHARED_PREFS_BTN_COUNTER= "BtnCounter";
    public static final String SHARED_PREFS_BACKGROUND_COUNTER= "BgCounter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadData();

        tvBtnCount = findViewById(a_main_tv_btnCount);
        tvBgCount = findViewById(R.id.a_main_tv_btnBgCount);
        clickButton = findViewById(R.id.a_main_btn1);

        tvBtnCount.setText("Button Count "+Integer.toString(btnCounter));
        tvBgCount.setText("Background Count "+Integer.toString(bgCounter));

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnCounter ++;
                tvBtnCount.setText("Button Count "+Integer.toString(btnCounter));
                saveData();

            }
        });

        if (savedInstanceState == null) {
            lastOrientation = getResources().getConfiguration().orientation;
        }
    }

    @Override
    protected void onResume() {

        tvBgCount.setText("Background Count " + bgCounter);
        super.onResume();
    }

    @Override
    protected void onStop() {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            bgCounter++;
            tvBgCount.setText("Background Count " + bgCounter);

        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            bgCounter++;
            tvBgCount.setText("Background Count " + bgCounter);
        }
        saveData();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHARED_PREFS_BTN_COUNTER,btnCounter);
        outState.putInt(SHARED_PREFS_BACKGROUND_COUNTER,bgCounter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        btnCounter =savedInstanceState.getInt(SHARED_PREFS_BTN_COUNTER);
        bgCounter = savedInstanceState.getInt(SHARED_PREFS_BACKGROUND_COUNTER);
        tvBgCount = findViewById(R.id.a_main_tv_btnBgCount);
        tvBgCount.setText(Integer.toString(bgCounter));
    }

    private void saveData(){
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt(SHARED_PREFS_BTN_COUNTER,btnCounter);
        editor.putInt(SHARED_PREFS_BACKGROUND_COUNTER,bgCounter);
        editor.apply();
        editor.commit();
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        btnCounter = sharedPreferences.getInt(SHARED_PREFS_BTN_COUNTER, 0);
        bgCounter = sharedPreferences.getInt(SHARED_PREFS_BACKGROUND_COUNTER,0);
    }
}

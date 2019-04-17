package com.example.clickcounter;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;



@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private int lastOrientation;
    public static final String KEY_LAST_ORIENTATION = "last_orientation";

    TextView tvBtnCount;
    TextView tvBgCount;
    Button button1;
    private int BtnCounter = 0;
    private int BgCounter = 0;
    protected static final String TAG = MainActivity.class.getName();

    public static final String shared_prefs = "shared preferences";
    public static final String TEXT1= "BtnCounter";
    public static final String TEXT2= "BgCounter";

    @Override
    protected void onResume() {


        BgCounter = BgCounter;
        tvBgCount.setText("Background Count " + BgCounter);

        super.onResume();
        Log.i("TAG","onResume");

    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onPause() {

        Log.i("TAG","ONPAUSE");
       // saveData();
        super.onPause();
    }

    @Override
    protected void onStop() {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {

            BgCounter++;
            tvBgCount.setText("Background Count " + BgCounter);

            Log.i("TAG", "OnStop if portrait");

        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            BgCounter++;
            tvBgCount.setText("Background Count " + BgCounter);

            Log.i("TAG", "ONPAUSE if landscape");

        }
        else {
            //BgCounter++;
            tvBgCount.setText("Background Count "+Integer.toString(BgCounter));
            Log.i("TAG", "OnStop else");
        }

        saveData();
        Log.i("TAG","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("TAG","onDestoy");
        saveData();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i("TAG","onRestart");
        super.onRestart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadData();

        tvBtnCount = findViewById(R.id.a_main_tv_btnCount);
        tvBgCount = findViewById(R.id.a_main_tv_btnBgCount);
        button1 = findViewById(R.id.a_main_btn1);


        tvBtnCount.setText("Button Count "+Integer.toString(BtnCounter));
        tvBgCount.setText("Background Count "+Integer.toString(BgCounter));
        Log.i("tvBtnCounter",Integer.toString(BtnCounter));
        Log.i("tvBgCounter",Integer.toString(BgCounter));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BtnCounter ++;
                tvBtnCount.setText("Button Count "+Integer.toString(BtnCounter));
                saveData();

            }
        });


        if (savedInstanceState == null) {
            lastOrientation = getResources().getConfiguration().orientation;
        }
    }

    public void saveData(){
        SharedPreferences sharedPreferences =getSharedPreferences(shared_prefs,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt(TEXT1,BtnCounter);
        editor.putInt(TEXT2,BgCounter);
        editor.apply();
        editor.commit();

        //Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show();
        Log.i("TAG","saveData");

    }

    public void loadData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(shared_prefs,MODE_PRIVATE);

        BtnCounter = sharedPreferences.getInt(TEXT1, 0);
        BgCounter = sharedPreferences.getInt(TEXT2,0);
        Log.i("TAG","loadData");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"App went to background");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TEXT1,BtnCounter);
        outState.putInt(TEXT2,BgCounter);

        Log.i("TAG","onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BtnCounter =savedInstanceState.getInt(TEXT1);
        BgCounter = savedInstanceState.getInt(TEXT2);
        tvBgCount = findViewById(R.id.a_main_tv_btnBgCount);
        tvBgCount.setText(Integer.toString(BgCounter));
        Log.i("TAG","onRestoreInstanceState");


    }
/*      Added for layout change*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }
}

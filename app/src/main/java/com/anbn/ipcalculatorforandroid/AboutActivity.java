package com.anbn.ipcalculatorforandroid;

import android.view.View;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity {

    // нарисуем экран
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    // метод нажатия на кнопку
    public void onClickButtonBack(View v) {
        // возврат на предыдущий activity
        onBackPressed();
    }


}

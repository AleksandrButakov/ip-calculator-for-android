package com.anbn.ipcalculatorforandroid;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    // нарисуем экран
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        TextView textViewPrivacyPolicy = (TextView) findViewById(R.id.textViewPrivacyPolicy);
        textViewPrivacyPolicy.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    // метод нажатия на кнопку
    public void onClickButtonBack(View v) {
        // возврат на предыдущий activity
        onBackPressed();
    }

}

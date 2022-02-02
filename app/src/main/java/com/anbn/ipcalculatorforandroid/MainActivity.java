package com.anbn.ipcalculatorforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.sql.SQLOutput;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    com.anbn.ipcalculatorforandroid.PageAdapter pageAdapter;

    public TextView textView;
    public EditText editText;
    public EditText ipAddressEdit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        TabItem tabItem1 = (TabItem) findViewById(R.id.tab1);
        TabItem tabItem2 = (TabItem) findViewById(R.id.tab2);
        TabItem tabItem3 = (TabItem) findViewById(R.id.tab3);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpager);

        pageAdapter = new com.anbn.ipcalculatorforandroid.PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));





    }





    // TAB_01
    // изменение значения в поле EditText CIDR


//android:onClick="onClickIPAddress1"





    public void listenerEditText() {

        System.out.println("Listener IP address...");
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        TextView textViewIP = (TextView) findViewById(R.id.textView1);
        ipAddressEdit1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s2;
                EditText ipAddress1 = (EditText) findViewById(R.id.ipAddressEdit1);
                s2 = String.valueOf(ipAddress1.getText());
                textViewIP.setText(s2);
            }
        });

        System.out.println("Listener CIDR");
        EditText cIDR1 = (EditText) findViewById(R.id.cidr1);
        cIDR1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s2;
                EditText ipAddress1 = (EditText) findViewById(R.id.ipAddressEdit1);
                s2 = String.valueOf(cIDR1.getText());
                textViewIP.setText(s2);
            }
        });

        System.out.println("Listener netmaskEdit1");
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
        netmaskEdit1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s2;
                EditText ipAddress1 = (EditText) findViewById(R.id.ipAddressEdit1);
                s2 = String.valueOf(netmaskEdit1.getText());
                textViewIP.setText(s2);
            }
        });

    }



    // нажатие кнопки CLEAR1, очишаем все поля ввода нулевой вкладки
    public void onClickClearButton1 (View v) {
        clearingFragment1Fields();
        clearingFragment1Data();
    }

    // обнулим значение переменных используемых для расчета
    public void clearingFragment1Fields() {
        CalculationAddresses ftab1 = new CalculationAddresses();
        ftab1.ipAddress = "";
        ftab1.sIDR = "";
        ftab1.networkMask = "";

        ftab1.decNetwork = "";
        ftab1.decBroadcast = "";
        ftab1.decNetMask = "";
        ftab1.decFirstAddress = "";
        ftab1.decLastAddress = "";
        ftab1.decUsable = "";

        ftab1.binNetwork = "";
        ftab1.binBroadcast = "";
        ftab1.binNetmask = "";
        ftab1.binFirstAddress = "";
        ftab1.binLastAddress = "";
        ftab1.binUsable = "";
    }

    // обозначим поля ввода и вывода информации
    public void clearingFragment1Data() {
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cidr1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        TextView decUsableText1 = (TextView) findViewById(R.id.decUsableText1);
        TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
        TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
        TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
        TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
        TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);
        TextView binUsableText1 = (TextView) findViewById(R.id.binUsableText1);

        ipAddressEdit1.setText("");
        cidr1.setText("");
        netmaskEdit1.setText("");
        decNetworkIPText1.setText("");
        decBroadcastText1.setText("");
        decNetmaskText1.setText("");
        decFirstAddressText1.setText("");
        decLastAddressText1.setText("");
        decUsableText1.setText("");
        binNetworkText1.setText("");
        binBroadcastText1.setText("");
        binNetmaskText1.setText("");
        binFirstAddressText1.setText("");
        binLastAddressText1.setText("");
        binUsableText1.setText("");
    }

    // нажатие кнопки CALC, проверяем корректность введенных данных и выводим результат
    public void onClickCalcButton1 (View v) {
        Toast.makeText(this, "Функционал находится в разработке...",
                Toast.LENGTH_SHORT).show();
        listenerEditText();

    }



    //TAB_02
    public void onClickCalcButton2 (View v) {
        EditText cidr2 = (EditText) findViewById(R.id.editText21);
        cidr2.setText("It's a working!!!");
    }

    public void onSw (View v) {
        System.out.println("3333");

        EditText cidr2 = (EditText) findViewById(R.id.editText21);
        Switch sw = (Switch) findViewById(R.id.switch1);
        //sw.setClickable(false);
        listenerEditText();

    }



}
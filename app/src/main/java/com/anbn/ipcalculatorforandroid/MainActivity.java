package com.anbn.ipcalculatorforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {

    com.anbn.ipcalculatorforandroid.PageAdapter pageAdapter;

    public TextView textView;
    public EditText editText;

    public static String sIPCorrectly = "";
    public static String sIPIncorrectly = "";

    public static String sCIDRCorrectly = "";
    public static String sCIDRIncorrectly = "";

    public static String sNetmaskCorrectly = "";
    public static String sNetmaskIncorrectly = "";

    public static int iPos;
    public static boolean valueFieldChangedByUser = true;

    // переменные для определения активности вкладок
    public static boolean tab1IsActive = false;
    public static boolean tab2IsActive = false;
    public static boolean tab3IsActive = false;

    // переменные для определения впервые ли включена вкладка
    public static boolean firstLaunchTab1 = true;
    public static boolean firstLaunchTab2 = true;
    public static boolean firstLaunchTab3 = true;

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
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }



    // задаем listener для поля ввода IP адреса
    public void listenerEditText() {
        System.out.println("Listener IP address...");

        TextView textViewIP = (TextView) findViewById(R.id.textView1);
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);

        ipAddressEdit1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueFieldChangedByUser == true) {
                    if (!ipAddressEdit1.getText().equals("")) {
                        iPos = ipAddressEdit1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueFieldChangedByUser == true) {

                    String sIP;
                    sIP = String.valueOf(ipAddressEdit1.getText());
                    //ipAddressEdit1.setSelection(iPos);

                    // если введен некорректный IP адрес
                    if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIP)) {
                        // введен корректный IP адрес
                        sIPCorrectly = sIP;
                        iPos = ipAddressEdit1.getSelectionStart();
                        textViewIP.setText(String.valueOf(iPos));

                    } else {
                        // введен неверный IP адрес
                        valueFieldChangedByUser = false;
                        textViewIP.setText(String.valueOf(iPos));
                        ipAddressEdit1.setText(sIPCorrectly);
                        ipAddressEdit1.setSelection(iPos - 1);
                        //information();
                    }
                }
                valueFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода CIDR
        System.out.println("Listener CIDR");
        EditText cIDR1 = (EditText) findViewById(R.id.cidr1);
        cIDR1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueFieldChangedByUser == true) {
                    if (!cIDR1.getText().equals("")) {
                        iPos = cIDR1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueFieldChangedByUser == true) {

                    String sCIDR;
                    sCIDR = String.valueOf(cIDR1.getText());
                    //ipAddressEdit1.setSelection(iPos);

                    // если введен некорректный IP адрес
                    if (CheckingCorrectnessCIDR.checkingCorrectnessCIDR(sCIDR)) {
                        // введен корректный IP адрес
                        sCIDRCorrectly = sCIDR;
                        iPos = cIDR1.getSelectionStart();
                        textViewIP.setText(String.valueOf(iPos));

                    } else {
                        // введен неверный IP адрес
                        valueFieldChangedByUser = false;
                        textViewIP.setText(String.valueOf(iPos));
                        cIDR1.setText(sCIDRCorrectly);
                        cIDR1.setSelection(iPos - 1);
                        //information();
                    }
                }
                valueFieldChangedByUser = true;
            }
        });





        // задаем listener для поля ввода netmaskEdit1
        System.out.println("Listener netmaskEdit1");
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
        netmaskEdit1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueFieldChangedByUser == true) {
                    if (!netmaskEdit1.getText().equals("")) {
                        iPos = netmaskEdit1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueFieldChangedByUser == true) {

                    String sNetmask;
                    sNetmask = String.valueOf(netmaskEdit1.getText());
                    //ipAddressEdit1.setSelection(iPos);

                    // если введен некорректный IP адрес
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(sNetmask)) {
                        // введен корректный IP адрес
                        sNetmaskCorrectly = sNetmask;
                        iPos = netmaskEdit1.getSelectionStart();
                        textViewIP.setText(String.valueOf(iPos));

                    } else {
                        // введен неверный IP адрес
                        valueFieldChangedByUser = false;
                        textViewIP.setText(String.valueOf(iPos));
                        netmaskEdit1.setText(sNetmaskCorrectly);
                        netmaskEdit1.setSelection(iPos - 1);
                        //information();
                    }
                }
                valueFieldChangedByUser = true;

            }
        });
    }




    public void information() {
        Toast toast = Toast.makeText(MainActivity.this,
                "Invalid IP address entered...",
                Toast.LENGTH_SHORT);
        toast.show();

    }


    // нажатие кнопки CLEAR1, очишаем все поля ввода нулевой вкладки
    public void onClickClearButton1(View v) {
        valueFieldChangedByUser = false;
        ClearingFragment1Fields.clearingFragment1Fields();
        clearingFragment1Data();
    }


    // нажатие кнопки CALC, проверяем корректность введенных данных и выводим результат
    public void onClickCalcButton1(View v) {
        Toast.makeText(this, "Функционал находится в разработке...",
                Toast.LENGTH_SHORT).show();
        listenerEditText();

    }

    //TAB_02
    public void onClickCalcButton2(View v) {
        EditText cidr2 = (EditText) findViewById(R.id.editText21);
        cidr2.setText("It's a working!!!");
    }

    public void onSw(View v) {
        Switch sw = (Switch) findViewById(R.id.switch1);
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cidr1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);

        // в случае если вкладка была активная, делаем поля EditText setEnabled(false)
        // в случае если вкладка была не активная, делаем поля EditText setEnabled(true)
        if (tab1IsActive) {
            ipAddressEdit1.setEnabled(false);
            cidr1.setEnabled(false);
            netmaskEdit1.setEnabled(false);
            tab1IsActive = false;

            Toast.makeText(this, "111",
                    Toast.LENGTH_SHORT).show();
        } else {
            ipAddressEdit1.setEnabled(true);
            cidr1.setEnabled(true);
            netmaskEdit1.setEnabled(true);
            tab1IsActive = true;

            Toast.makeText(this, "222",
                    Toast.LENGTH_SHORT).show();
        }

        // при первом запуске вкладки активируем Listener
        if (firstLaunchTab1) {
            listenerEditText();
            firstLaunchTab1 = false;
            Toast.makeText(this, "333",
                    Toast.LENGTH_SHORT).show();
        }

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

        valueFieldChangedByUser = true;
    }


}
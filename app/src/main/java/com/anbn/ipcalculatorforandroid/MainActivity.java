package com.anbn.ipcalculatorforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    com.anbn.ipcalculatorforandroid.PageAdapter pageAdapter;

    public static String sIPCorrectly = "";
    public static String sIPCorrectlyB3 = "";
    public static String sIPCorrectlyB2 = "";
    public static String sIPCorrectlyB1 = "";
    public static String sIPCorrectlyB0 = "";

    public static String sCIDRCorrectly = "";

    public static String sNetmaskCorrectly = "";
    public static String sNetmaskCorrectlyB3 = "";
    public static String sNetmaskCorrectlyB2 = "";
    public static String sNetmaskCorrectlyB1 = "";
    public static String sNetmaskCorrectlyB0 = "";

    public static int iPos;
    public static boolean valueIPAddressFieldChangedByUser = true;
    public static boolean valueCIDRFieldChangedByUser = true;
    public static boolean valueNetmaskFieldChangedByUser = true;

    // переменные для определения активности вкладок
    public static boolean tab1IsActive = false;
    public static boolean tab2IsActive = false;
    public static boolean tab3IsActive = false;

    // переменные для определения впервые ли включена вкладка
    public static boolean firstLaunchTab1 = true;
    public static boolean firstLaunchTab2 = true;
    public static boolean firstLaunchTab3 = true;

    public static String auxiliaryVariables = "";

    CalculationAddresses tab1 = new CalculationAddresses();

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

        /* очистим переменные вкладки Tab1 при запуске программы. Необходимо для исключения
        равенства переменной значению null
        */
        ClearingFragment1Fields.clearingVariablesTab1(tab1);

    }


    // переключатель Switch Tab1, активирует и деактивирует EditText-ы на странице
    // предварительно проверив делается ли это впервые
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

            // Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
        } else {
            ipAddressEdit1.setEnabled(true);
            cidr1.setEnabled(true);
            netmaskEdit1.setEnabled(true);
            tab1IsActive = true;

            // Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();
        }

        // при первом запуске вкладки активируем Listener
        if (firstLaunchTab1) {
            listenerEditText();
            firstLaunchTab1 = false;
            // Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
        }
    }


    // задаем listener для поля ввода IP адреса
    public void listenerEditText() {
        System.out.println("Listener IP address...");

        TextView textViewIP = (TextView) findViewById(R.id.textView1);

        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cIDR1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);

        ipAddressEdit1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueIPAddressFieldChangedByUser == true) {
                    if (!ipAddressEdit1.getText().equals("")) {
                        iPos = ipAddressEdit1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueIPAddressFieldChangedByUser == true) {

                    String sIP;
                    sIP = String.valueOf(ipAddressEdit1.getText());

                    // если введен некорректный IP адрес
                    if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIP)) {
                        // введен корректный IP адрес
                        clearOutputFields();
                        sIPCorrectly = sIP;
                        iPos = ipAddressEdit1.getSelectionStart();
                        // textViewIP.setText(String.valueOf(iPos));

                        // проверим что все байты адреса заполнены
                        if (!sIPCorrectlyB3.equals("") && !sIPCorrectlyB2.equals("") &&
                                !sIPCorrectlyB1.equals("") && !sIPCorrectlyB0.equals("")) {
                            tab1.ipAddressB3 = sIPCorrectlyB3;
                            tab1.ipAddressB2 = sIPCorrectlyB2;
                            tab1.ipAddressB1 = sIPCorrectlyB1;
                            tab1.ipAddressB0 = sIPCorrectlyB0;
                        } else {
                            tab1.ipAddressB3 = "";
                            tab1.ipAddressB2 = "";
                            tab1.ipAddressB1 = "";
                            tab1.ipAddressB0 = "";
                        }

                    } else {
                        // введен неверный IP адрес
                        valueIPAddressFieldChangedByUser = false;
                        // textViewIP.setText(String.valueOf(iPos));
                        ipAddressEdit1.setText(sIPCorrectly);
                        ipAddressEdit1.setSelection(iPos - 1);

                        /*
                        tab1.ipAddressB3 = "";
                        tab1.ipAddressB2 = "";
                        tab1.ipAddressB1 = "";
                        tab1.ipAddressB0 = "";
                         */
                    }
                }
                valueIPAddressFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода CIDR
        System.out.println("Listener CIDR");
        cIDR1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueCIDRFieldChangedByUser == true) {
                    if (!cIDR1.getText().equals("")) {
                        iPos = cIDR1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueCIDRFieldChangedByUser == true) {

                    String sCIDR;
                    sCIDR = String.valueOf(cIDR1.getText());

                    // если введен корректный или некорректный IP адрес
                    if (CheckingCorrectnessCIDR.checkingCorrectnessCIDR(sCIDR)) {
                        // введен корректный IP адрес
                        clearOutputFields();
                        sCIDRCorrectly = sCIDR;
                        iPos = cIDR1.getSelectionStart();
                        // textViewIP.setText(String.valueOf(iPos));

                        // присвоим корректное значение маски переменной
                        // CalculationAddresses tab1 = new CalculationAddresses();
                        tab1.cidr = sCIDRCorrectly;
                        // заполним поле netmask корректным значением
                        valueNetmaskFieldChangedByUser = false;
                        auxiliaryVariables = CheckingCorrectnessCIDR.
                                searchForNetmaskByCIDR(sCIDRCorrectly);
                        netmaskEdit1.setText(auxiliaryVariables);
                        // присвоим переменной корректное значение для дальнейших вычислений
                        sNetmaskCorrectly = auxiliaryVariables;
                        // заполним значения байт маски для дальнейших вычислений
                        CheckingCorrectnessNetmask.checkingCorrectnessNetmask(auxiliaryVariables);
                        tab1.netmaskB3 = sNetmaskCorrectlyB3;
                        tab1.netmaskB2 = sNetmaskCorrectlyB2;
                        tab1.netmaskB1 = sNetmaskCorrectlyB1;
                        tab1.netmaskB0 = sNetmaskCorrectlyB0;

                    } else {
                        // введен неверный IP адрес
                        valueCIDRFieldChangedByUser = false;
                        // textViewIP.setText(String.valueOf(iPos));
                        cIDR1.setText(sCIDRCorrectly);
                        cIDR1.setSelection(iPos - 1);

                        // CalculationAddresses tab1 = new CalculationAddresses();
                        tab1.cidr = "";
                        tab1.netmaskB3 = "";
                        tab1.netmaskB2 = "";
                        tab1.netmaskB1 = "";
                        tab1.netmaskB0 = "";
                    }
                }
                valueCIDRFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода netmaskEdit1
        System.out.println("Listener netmaskEdit1");
        netmaskEdit1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueNetmaskFieldChangedByUser == true) {
                    if (!netmaskEdit1.getText().equals("")) {
                        iPos = netmaskEdit1.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueNetmaskFieldChangedByUser == true) {

                    String sNetmask;
                    sNetmask = String.valueOf(netmaskEdit1.getText());
                    //ipAddressEdit1.setSelection(iPos);

                    // если введен корректный или некорректный IP адрес
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(sNetmask)) {
                        // введен корректный IP адрес
                        clearOutputFields();
                        sNetmaskCorrectly = sNetmask;
                        iPos = netmaskEdit1.getSelectionStart();
                        // textViewIP.setText(String.valueOf(iPos));

                        // проверим что в поле netmask введено полное корректное значение по
                        // которому можно определить маску CIDR и в таком случае заполним поле
                        // CIDR соответствующим значением
                        auxiliaryVariables = CheckingCorrectnessNetmask.
                                searchForCIDRByNetmask(sNetmaskCorrectly);
                        if (!auxiliaryVariables.equals("")) {
                            valueCIDRFieldChangedByUser = false;
                            cIDR1.setText(auxiliaryVariables);

                            // заполним значения байт netmask and CIDR для дальнейших вычислений
                            tab1.cidr = auxiliaryVariables;
                            tab1.netmaskB3 = sNetmaskCorrectlyB3;
                            tab1.netmaskB2 = sNetmaskCorrectlyB2;
                            tab1.netmaskB1 = sNetmaskCorrectlyB1;
                            tab1.netmaskB0 = sNetmaskCorrectlyB0;

                        } else {
                            valueCIDRFieldChangedByUser = false;
                            cIDR1.setText("");

                            // очистим значения байт netmask and CIDR, т.к. текущие
                            // значения некорректны
                            tab1.cidr = auxiliaryVariables;
                            tab1.netmaskB3 = sNetmaskCorrectlyB3;
                            tab1.netmaskB2 = sNetmaskCorrectlyB2;
                            tab1.netmaskB1 = sNetmaskCorrectlyB1;
                            tab1.netmaskB0 = sNetmaskCorrectlyB0;

                        }

                    } else {
                        // введена неверная маска IP адрес
                        valueNetmaskFieldChangedByUser = false;
                        // textViewIP.setText(String.valueOf(iPos));
                        netmaskEdit1.setText(sNetmaskCorrectly);
                        netmaskEdit1.setSelection(iPos - 1);

                        /*
                        tab1.netmaskB3 = "";
                        tab1.netmaskB2 = "";
                        tab1.netmaskB1 = "";
                        tab1.netmaskB0 = "";
                         */
                    }
                }
                valueNetmaskFieldChangedByUser = true;
            }
        });
    }


    public void information() {
        Toast toast = Toast.makeText(MainActivity.this,
                "Invalid IP address entered...",
                Toast.LENGTH_SHORT);
        toast.show();
    }


    // нажатие кнопки CALC Tab1, проверяем корректность введенных данных и выводим результат
    public void onClickCalcButtonTab1(View v) {
        // проверим что переменные для хранения байтов IP адреса не равны null
        if (tab1.ipAddressB3.equals("") ||
                tab1.ipAddressB2.equals("") ||
                tab1.ipAddressB1.equals("") ||
                tab1.ipAddressB0.equals("") ||
                // проверим что переменная для хранения количества бит маски подсети не равна null
                tab1.cidr.equals("") ||
                // проверим что переменные для хранения байтов маски подсети не равны null
                tab1.netmaskB3.equals("") ||
                tab1.netmaskB2.equals("") ||
                tab1.netmaskB1.equals("") ||
                tab1.netmaskB0.equals("")) {
            // введены некорректные данные
            Toast.makeText(this, "Incorrect data entered...",
                    Toast.LENGTH_SHORT).show();
        } else {
            // введены корректные данные. Рассчитываем все параметры и выводим на экран
            EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
            EditText cidr1 = (EditText) findViewById(R.id.cidr1);
            EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);

            TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
            TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
            TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
            TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
            TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
            TextView decNumberHostsText1 = (TextView) findViewById(R.id.decNumberHostsText1);
            TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
            TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
            TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
            TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
            TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);

            // заполним массив boolean[] binIPAddressArray
            CalculationAddresses.fillingTheBinIPAddressArray(tab1);

            // заполним массив boolean[] binNetmaskArray
            CalculationAddresses.fillingTheBinNetmaskArray(tab1);
            binNetmaskText1.setText(tab1.sNetmaskBin);

            // рассчитываем значение binNetwork[32] and decNetwork и выводим на экран
            CalculationAddresses.fillingTheBinNetworkArray(tab1);
            decNetworkIPText1.setText(tab1.decNetwork);
            binNetworkText1.setText(tab1.sNetworkBin);

            // выводим параметр netmask на экран
            decNetmaskText1.setText(netmaskEdit1.getText());


            // рассчитываем параметр tab1.decNumberHosts и выводим на экран
            CalculationAddresses.calculationNumberHosts(tab1);
            decNumberHostsText1.setText(tab1.decNumberHosts);

            /*
            убираем фокус
            ipAddressEdit1.clearFocus();
            cidr1.clearFocus();
            netmaskEdit1.clearFocus();
             */

            // сворачиваем клавиатуру при нажатии на кнопку
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ipAddressEdit1.getWindowToken(), 0);

        }
    }


    // нажатие кнопки CLEAR1, очишаем все поля ввода нулевой вкладки
    public void onClickClearButtonTab1(View v) {
        valueIPAddressFieldChangedByUser = false;
        valueCIDRFieldChangedByUser = false;
        valueNetmaskFieldChangedByUser = false;

        ClearingFragment1Fields.clearingVariablesTab1(tab1);

        clearingFragment1Data();
    }


    //TAB_02
    public void onClickCalcButton2(View v) {
        EditText cidr2 = (EditText) findViewById(R.id.editText21);
        cidr2.setText("It's a working!!!");
    }


    // очищаем все поля экрана Tab1
    public void clearingFragment1Data() {
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cidr1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);
        TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
        TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
        TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
        TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
        TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);

        ipAddressEdit1.setText("");
        cidr1.setText("");
        netmaskEdit1.setText("");
        decNetworkIPText1.setText("");
        decBroadcastText1.setText("");
        decNetmaskText1.setText("");
        decFirstAddressText1.setText("");
        decLastAddressText1.setText("");
        decNumberText1.setText("");
        binNetworkText1.setText("");
        binBroadcastText1.setText("");
        binNetmaskText1.setText("");
        binFirstAddressText1.setText("");
        binLastAddressText1.setText("");

        valueIPAddressFieldChangedByUser = true;
        valueCIDRFieldChangedByUser = true;
        valueNetmaskFieldChangedByUser = true;
    }

    public void clearOutputFields() {
        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);
        TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
        TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
        TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
        TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
        TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);

        decNetworkIPText1.setText("");
        decBroadcastText1.setText("");
        decNetmaskText1.setText("");
        decFirstAddressText1.setText("");
        decLastAddressText1.setText("");
        decNumberText1.setText("");
        binNetworkText1.setText("");
        binBroadcastText1.setText("");
        binNetmaskText1.setText("");
        binFirstAddressText1.setText("");
        binLastAddressText1.setText("");
    }

}
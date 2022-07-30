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

    // переменные для определения впервые ли включена вкладка
    public static boolean firstLaunchTab1 = true;
    public static boolean firstLaunchTab2 = true;

    public static String auxiliaryVariables = "";

    CalculationAddresses tab1 = new CalculationAddresses();
    CalculationAddresses tab2 = new CalculationAddresses();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        TabItem tabItem1 = (TabItem) findViewById(R.id.tab1);
        TabItem tabItem2 = (TabItem) findViewById(R.id.tab2);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpager);

        pageAdapter = new com.anbn.ipcalculatorforandroid.PageAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1)
                    pageAdapter.notifyDataSetChanged();
                //listenerEditText();
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
        ClearingFragment1Fields.clearingVariablesTab(tab1);
        ClearingFragment1Fields.clearingVariablesTab(tab2);
    }


    // переключатель Switch Tab1, активирует и деактивирует EditText-ы на странице
    // предварительно проверив делается ли это впервые
    public void onSwitch1(View v) {
        //Switch sw1 = (Switch) findViewById(R.id.switch1);

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
        } else {
            ipAddressEdit1.setEnabled(true);
            cidr1.setEnabled(true);
            netmaskEdit1.setEnabled(true);
            tab1IsActive = true;
        }

        // при первом запуске вкладки активируем Listener
        if (firstLaunchTab1) {
            listenerEditText1();
            firstLaunchTab1 = false;
        }
    }


    // переключатель Switch Tab2, активирует и деактивирует EditText-ы на странице
    // предварительно проверив делается ли это впервые
    public void onSwitch2(View v) {
        //Switch sw2 = (Switch) findViewById(R.id.switch2);

        EditText ipAddressEdit2 = findViewById(R.id.ipAddressEdit2);
        EditText cidr2 = findViewById(R.id.cidr2);
        EditText netmaskEdit2 = findViewById(R.id.netmaskEdit2);

        // в случае если вкладка была активная, делаем поля EditText setEnabled(false)
        // в случае если вкладка была не активная, делаем поля EditText setEnabled(true)
        if (tab2IsActive) {
            ipAddressEdit2.setEnabled(false);
            cidr2.setEnabled(false);
            netmaskEdit2.setEnabled(false);
            tab2IsActive = false;
        } else {
            ipAddressEdit2.setEnabled(true);
            cidr2.setEnabled(true);
            netmaskEdit2.setEnabled(true);
            tab2IsActive = true;
        }

        // при первом запуске вкладки активируем Listener
        if (firstLaunchTab2) {
            listenerEditText2();
            firstLaunchTab2 = false;
        }
    }


    // задаем listener для поля ввода IP адреса вкладки Tab1
    public void listenerEditText1() {
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cIDR1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);

        // Listener IP address tab1
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
                        clearOutputFields1();
                        sIPCorrectly = sIP;
                        iPos = ipAddressEdit1.getSelectionStart();

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
                        ipAddressEdit1.setText(sIPCorrectly);
                        ipAddressEdit1.setSelection(iPos - 1);
                    }
                }
                valueIPAddressFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода CIDR Tab1
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
                        clearOutputFields1();
                        sCIDRCorrectly = sCIDR;
                        iPos = cIDR1.getSelectionStart();

                        // присвоим корректное значение маски переменной
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


        // задаем listener для поля ввода netmaskEdit1 Tab1
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

                    // если введен корректный или некорректный IP адрес
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(sNetmask)) {
                        // введен корректный IP адрес
                        clearOutputFields1();
                        sNetmaskCorrectly = sNetmask;
                        iPos = netmaskEdit1.getSelectionStart();

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
                        netmaskEdit1.setText(sNetmaskCorrectly);
                        netmaskEdit1.setSelection(iPos - 1);
                    }
                }
                valueNetmaskFieldChangedByUser = true;
            }
        });
    }


    // задаем listener для поля ввода IP адреса вкладки Tab2
    public void listenerEditText2() {
        EditText ipAddressEdit2 = (EditText) findViewById(R.id.ipAddressEdit2);
        EditText cIDR2 = (EditText) findViewById(R.id.cidr2);
        EditText netmaskEdit2 = (EditText) findViewById(R.id.netmaskEdit2);

        // Listener IP address tab2
        ipAddressEdit2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueIPAddressFieldChangedByUser == true) {
                    if (!ipAddressEdit2.getText().equals("")) {
                        iPos = ipAddressEdit2.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueIPAddressFieldChangedByUser == true) {

                    String sIP;
                    sIP = String.valueOf(ipAddressEdit2.getText());

                    // если введен некорректный IP адрес
                    if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIP)) {
                        // введен корректный IP адрес
                        clearOutputFields2();
                        sIPCorrectly = sIP;
                        iPos = ipAddressEdit2.getSelectionStart();

                        // проверим что все байты адреса заполнены
                        if (!sIPCorrectlyB3.equals("") && !sIPCorrectlyB2.equals("") &&
                                !sIPCorrectlyB1.equals("") && !sIPCorrectlyB0.equals("")) {
                            tab2.ipAddressB3 = sIPCorrectlyB3;
                            tab2.ipAddressB2 = sIPCorrectlyB2;
                            tab2.ipAddressB1 = sIPCorrectlyB1;
                            tab2.ipAddressB0 = sIPCorrectlyB0;
                        } else {
                            tab2.ipAddressB3 = "";
                            tab2.ipAddressB2 = "";
                            tab2.ipAddressB1 = "";
                            tab2.ipAddressB0 = "";
                        }

                    } else {
                        // введен неверный IP адрес
                        valueIPAddressFieldChangedByUser = false;
                        ipAddressEdit2.setText(sIPCorrectly);
                        ipAddressEdit2.setSelection(iPos - 1);
                    }
                }
                valueIPAddressFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода CIDR Tab2
        cIDR2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueCIDRFieldChangedByUser == true) {
                    if (!cIDR2.getText().equals("")) {
                        iPos = cIDR2.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueCIDRFieldChangedByUser == true) {

                    String sCIDR;
                    sCIDR = String.valueOf(cIDR2.getText());

                    // если введен корректный или некорректный IP адрес
                    if (CheckingCorrectnessCIDR.checkingCorrectnessCIDR(sCIDR)) {
                        // введен корректный IP адрес
                        clearOutputFields2();
                        sCIDRCorrectly = sCIDR;
                        iPos = cIDR2.getSelectionStart();

                        // присвоим корректное значение маски переменной
                        tab2.cidr = sCIDRCorrectly;
                        // заполним поле netmask корректным значением
                        valueNetmaskFieldChangedByUser = false;
                        auxiliaryVariables = CheckingCorrectnessCIDR.
                                searchForNetmaskByCIDR(sCIDRCorrectly);
                        netmaskEdit2.setText(auxiliaryVariables);
                        // присвоим переменной корректное значение для дальнейших вычислений
                        sNetmaskCorrectly = auxiliaryVariables;
                        // заполним значения байт маски для дальнейших вычислений
                        CheckingCorrectnessNetmask.checkingCorrectnessNetmask(auxiliaryVariables);
                        tab2.netmaskB3 = sNetmaskCorrectlyB3;
                        tab2.netmaskB2 = sNetmaskCorrectlyB2;
                        tab2.netmaskB1 = sNetmaskCorrectlyB1;
                        tab2.netmaskB0 = sNetmaskCorrectlyB0;

                    } else {
                        // введен неверный IP адрес
                        valueCIDRFieldChangedByUser = false;
                        cIDR2.setText(sCIDRCorrectly);
                        cIDR2.setSelection(iPos - 1);

                        tab2.cidr = "";
                        tab2.netmaskB3 = "";
                        tab2.netmaskB2 = "";
                        tab2.netmaskB1 = "";
                        tab2.netmaskB0 = "";
                    }
                }
                valueCIDRFieldChangedByUser = true;
            }
        });


        // задаем listener для поля ввода netmaskEdit1 Tab2
        netmaskEdit2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (valueNetmaskFieldChangedByUser == true) {
                    if (!netmaskEdit2.getText().equals("")) {
                        iPos = netmaskEdit2.getSelectionStart() + 1;
                    }
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (valueNetmaskFieldChangedByUser == true) {

                    String sNetmask;
                    sNetmask = String.valueOf(netmaskEdit2.getText());

                    // если введен корректный или некорректный IP адрес
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(sNetmask)) {
                        // введен корректный IP адрес
                        clearOutputFields2();
                        sNetmaskCorrectly = sNetmask;
                        iPos = netmaskEdit2.getSelectionStart();

                        // проверим что в поле netmask введено полное корректное значение по
                        // которому можно определить маску CIDR и в таком случае заполним поле
                        // CIDR соответствующим значением
                        auxiliaryVariables = CheckingCorrectnessNetmask.
                                searchForCIDRByNetmask(sNetmaskCorrectly);
                        if (!auxiliaryVariables.equals("")) {
                            valueCIDRFieldChangedByUser = false;
                            cIDR2.setText(auxiliaryVariables);

                            // заполним значения байт netmask and CIDR для дальнейших вычислений
                            tab2.cidr = auxiliaryVariables;
                            tab2.netmaskB3 = sNetmaskCorrectlyB3;
                            tab2.netmaskB2 = sNetmaskCorrectlyB2;
                            tab2.netmaskB1 = sNetmaskCorrectlyB1;
                            tab2.netmaskB0 = sNetmaskCorrectlyB0;

                        } else {
                            valueCIDRFieldChangedByUser = false;
                            cIDR2.setText("");

                            // очистим значения байт netmask and CIDR, т.к. текущие
                            // значения некорректны
                            tab2.cidr = auxiliaryVariables;
                            tab2.netmaskB3 = sNetmaskCorrectlyB3;
                            tab2.netmaskB2 = sNetmaskCorrectlyB2;
                            tab2.netmaskB1 = sNetmaskCorrectlyB1;
                            tab2.netmaskB0 = sNetmaskCorrectlyB0;

                        }

                    } else {
                        // введена неверная маска IP адрес
                        valueNetmaskFieldChangedByUser = false;
                        netmaskEdit2.setText(sNetmaskCorrectly);
                        netmaskEdit2.setSelection(iPos - 1);
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

            TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
            TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
            TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
            TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
            TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
            TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);

            // заполним массив boolean[] binIPAddressArray
            CalculationAddresses.fillingTheBinIPAddressArray(tab1);
            binIPAddressText1.setText(tab1.sIPAddressBin);

            // заполним массив boolean[] binNetmaskArray
            CalculationAddresses.fillingTheBinNetmaskArray(tab1);
            binNetmaskText1.setText(tab1.sNetmaskBin);

            // рассчитываем значение binNetwork[32], decNetwork
            // binFirstAddress[32], binLastAddress[32], binBroadcast[32] и выводим на экран
            CalculationAddresses.fillingTheBinNetworkArray(tab1);
            decNetworkIPText1.setText(tab1.decNetwork);
            binNetworkText1.setText(tab1.sNetworkBin);
            decNetworkIPText1.setText(CalculationAddresses.binToDec(tab1.binNetworkArray));
            binFirstAddressText1.setText(tab1.sFirstAddressBin);
            decFirstAddressText1.setText(CalculationAddresses.binToDec(tab1.binFirstAddress));
            binLastAddressText1.setText(tab1.sLastAddressBin);
            decLastAddressText1.setText(CalculationAddresses.binToDec(tab1.binLastAddress));
            binBroadcastText1.setText(tab1.sBroadcastBin);
            decBroadcastText1.setText(CalculationAddresses.binToDec(tab1.binBroadcast));

            // выводим параметр netmask на экран
            decNetmaskText1.setText(netmaskEdit1.getText());

            // рассчитываем параметр tab1.decNumberHosts и выводим на экран
            CalculationAddresses.calculationNumberHosts(tab1);
            decNumberHostsText1.setText(tab1.decNumberHosts);

            // сворачиваем клавиатуру при нажатии на кнопку
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ipAddressEdit1.getWindowToken(), 0);

        }
    }


    // нажатие кнопки CALC Tab2, проверяем корректность введенных данных и выводим результат
    public void onClickCalcButtonTab2(View v) {
        // проверим что переменные для хранения байтов IP адреса не равны null
        if (tab2.ipAddressB3.equals("") ||
                tab2.ipAddressB2.equals("") ||
                tab2.ipAddressB1.equals("") ||
                tab2.ipAddressB0.equals("") ||
                // проверим что переменная для хранения количества бит маски подсети не равна null
                tab2.cidr.equals("") ||
                // проверим что переменные для хранения байтов маски подсети не равны null
                tab2.netmaskB3.equals("") ||
                tab2.netmaskB2.equals("") ||
                tab2.netmaskB1.equals("") ||
                tab2.netmaskB0.equals("")) {
            // введены некорректные данные
            Toast.makeText(this, "Incorrect data entered...",
                    Toast.LENGTH_SHORT).show();
        } else {
            // введены корректные данные. Рассчитываем все параметры и выводим на экран
            EditText ipAddressEdit2 = (EditText) findViewById(R.id.ipAddressEdit2);
            EditText cidr2 = (EditText) findViewById(R.id.cidr2);
            EditText netmaskEdit2 = (EditText) findViewById(R.id.netmaskEdit2);

            TextView decNetworkIPText2 = (TextView) findViewById(R.id.decNetworkIPText2);
            TextView decBroadcastText2 = (TextView) findViewById(R.id.decBroadcastText2);
            TextView decNetmaskText2 = (TextView) findViewById(R.id.decNetmaskText2);
            TextView decFirstAddressText2 = (TextView) findViewById(R.id.decFirstAddressText2);
            TextView decLastAddressText2 = (TextView) findViewById(R.id.decLastAddressText2);
            TextView decNumberHostsText2 = (TextView) findViewById(R.id.decNumberHostsText2);

            TextView binIPAddressText2 = (TextView) findViewById(R.id.binIPAddressText2);
            TextView binNetworkText2 = (TextView) findViewById(R.id.binNetworkText2);
            TextView binBroadcastText2 = (TextView) findViewById(R.id.binBroadcastText2);
            TextView binNetmaskText2 = (TextView) findViewById(R.id.binNetmaskText2);
            TextView binFirstAddressText2 = (TextView) findViewById(R.id.binFirstAddressText2);
            TextView binLastAddressText2 = (TextView) findViewById(R.id.binLastAddressText2);

            // заполним массив boolean[] binIPAddressArray
            CalculationAddresses.fillingTheBinIPAddressArray(tab2);
            binIPAddressText2.setText(tab2.sIPAddressBin);

            // заполним массив boolean[] binNetmaskArray
            CalculationAddresses.fillingTheBinNetmaskArray(tab2);
            binNetmaskText2.setText(tab2.sNetmaskBin);

            // рассчитываем значение binNetwork[32], decNetwork
            // binFirstAddress[32], binLastAddress[32], binBroadcast[32] и выводим на экран
            CalculationAddresses.fillingTheBinNetworkArray(tab2);
            decNetworkIPText2.setText(tab2.decNetwork);
            binNetworkText2.setText(tab2.sNetworkBin);
            decNetworkIPText2.setText(CalculationAddresses.binToDec(tab2.binNetworkArray));
            binFirstAddressText2.setText(tab2.sFirstAddressBin);
            decFirstAddressText2.setText(CalculationAddresses.binToDec(tab2.binFirstAddress));
            binLastAddressText2.setText(tab2.sLastAddressBin);
            decLastAddressText2.setText(CalculationAddresses.binToDec(tab2.binLastAddress));
            binBroadcastText2.setText(tab2.sBroadcastBin);
            decBroadcastText2.setText(CalculationAddresses.binToDec(tab2.binBroadcast));

            // выводим параметр netmask на экран
            decNetmaskText2.setText(netmaskEdit2.getText());

            // рассчитываем параметр tab1.decNumberHosts и выводим на экран
            CalculationAddresses.calculationNumberHosts(tab2);
            decNumberHostsText2.setText(tab2.decNumberHosts);

            // сворачиваем клавиатуру при нажатии на кнопку
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ipAddressEdit2.getWindowToken(), 0);

        }
    }


    // нажатие кнопки CLEAR1, очишаем все поля ввода нулевой вкладки
    public void onClickClearButtonTab1(View v) {
        valueIPAddressFieldChangedByUser = false;
        valueCIDRFieldChangedByUser = false;
        valueNetmaskFieldChangedByUser = false;
        ClearingFragment1Fields.clearingVariablesTab(tab1);
        clearingFragment1DataTab1();
    }


    // нажатие кнопки CLEAR1, очишаем все поля ввода нулевой вкладки
    public void onClickClearButtonTab2(View v) {
        valueIPAddressFieldChangedByUser = false;
        valueCIDRFieldChangedByUser = false;
        valueNetmaskFieldChangedByUser = false;
        ClearingFragment1Fields.clearingVariablesTab(tab2);
        clearingFragment1DataTab2();
    }


    // очищаем все поля экрана Tab1
    public void clearingFragment1DataTab1() {
        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
        EditText cidr1 = (EditText) findViewById(R.id.cidr1);
        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);

        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);

        TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
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

        binIPAddressText1.setText("");
        binNetworkText1.setText("");
        binBroadcastText1.setText("");
        binNetmaskText1.setText("");
        binFirstAddressText1.setText("");
        binLastAddressText1.setText("");

        valueIPAddressFieldChangedByUser = true;
        valueCIDRFieldChangedByUser = true;
        valueNetmaskFieldChangedByUser = true;
    }


    // очищаем все поля экрана Tab2
    public void clearingFragment1DataTab2() {
        EditText ipAddressEdit2 = (EditText) findViewById(R.id.ipAddressEdit2);
        EditText cidr2 = (EditText) findViewById(R.id.cidr2);
        EditText netmaskEdit2 = (EditText) findViewById(R.id.netmaskEdit2);

        TextView decNetworkIPText2 = (TextView) findViewById(R.id.decNetworkIPText2);
        TextView decBroadcastText2 = (TextView) findViewById(R.id.decBroadcastText2);
        TextView decNetmaskText2 = (TextView) findViewById(R.id.decNetmaskText2);
        TextView decFirstAddressText2 = (TextView) findViewById(R.id.decFirstAddressText2);
        TextView decLastAddressText2 = (TextView) findViewById(R.id.decLastAddressText2);
        TextView decNumberText2 = (TextView) findViewById(R.id.decNumberHostsText2);

        TextView binIPAddressText2 = (TextView) findViewById(R.id.binIPAddressText2);
        TextView binNetworkText2 = (TextView) findViewById(R.id.binNetworkText2);
        TextView binBroadcastText2 = (TextView) findViewById(R.id.binBroadcastText2);
        TextView binNetmaskText2 = (TextView) findViewById(R.id.binNetmaskText2);
        TextView binFirstAddressText2 = (TextView) findViewById(R.id.binFirstAddressText2);
        TextView binLastAddressText2 = (TextView) findViewById(R.id.binLastAddressText2);

        ipAddressEdit2.setText("");
        cidr2.setText("");
        netmaskEdit2.setText("");

        decNetworkIPText2.setText("");
        decBroadcastText2.setText("");
        decNetmaskText2.setText("");
        decFirstAddressText2.setText("");
        decLastAddressText2.setText("");
        decNumberText2.setText("");

        binIPAddressText2.setText("");
        binNetworkText2.setText("");
        binBroadcastText2.setText("");
        binNetmaskText2.setText("");
        binFirstAddressText2.setText("");
        binLastAddressText2.setText("");

        valueIPAddressFieldChangedByUser = true;
        valueCIDRFieldChangedByUser = true;
        valueNetmaskFieldChangedByUser = true;
    }


    public void clearOutputFields1() {
        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);

        TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
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
        binIPAddressText1.setText("");
        binNetworkText1.setText("");
        binBroadcastText1.setText("");
        binNetmaskText1.setText("");
        binFirstAddressText1.setText("");
        binLastAddressText1.setText("");
    }


    public void clearOutputFields2() {
        TextView decNetworkIPText2 = (TextView) findViewById(R.id.decNetworkIPText2);
        TextView decBroadcastText2 = (TextView) findViewById(R.id.decBroadcastText2);
        TextView decNetmaskText2 = (TextView) findViewById(R.id.decNetmaskText2);
        TextView decFirstAddressText2 = (TextView) findViewById(R.id.decFirstAddressText2);
        TextView decLastAddressText2 = (TextView) findViewById(R.id.decLastAddressText2);
        TextView decNumberText2 = (TextView) findViewById(R.id.decNumberHostsText2);

        TextView binIPAddressText2 = (TextView) findViewById(R.id.binIPAddressText2);
        TextView binNetworkText2 = (TextView) findViewById(R.id.binNetworkText2);
        TextView binBroadcastText2 = (TextView) findViewById(R.id.binBroadcastText2);
        TextView binNetmaskText2 = (TextView) findViewById(R.id.binNetmaskText2);
        TextView binFirstAddressText2 = (TextView) findViewById(R.id.binFirstAddressText2);
        TextView binLastAddressText2 = (TextView) findViewById(R.id.binLastAddressText2);

        decNetworkIPText2.setText("");
        decBroadcastText2.setText("");
        decNetmaskText2.setText("");
        decFirstAddressText2.setText("");
        decLastAddressText2.setText("");
        decNumberText2.setText("");
        binIPAddressText2.setText("");
        binNetworkText2.setText("");
        binBroadcastText2.setText("");
        binNetmaskText2.setText("");
        binFirstAddressText2.setText("");
        binLastAddressText2.setText("");
    }


}
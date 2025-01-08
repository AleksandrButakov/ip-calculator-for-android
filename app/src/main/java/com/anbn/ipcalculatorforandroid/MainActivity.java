package com.anbn.ipcalculatorforandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
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
    public static String url = "";

    Intent intentPrivacy;

    // temp new
    private EditText ipAddress;
    private EditText cidr;
    private EditText netmask;
    private boolean isUpdating = false; // Флаг для предотвращения зацикливания

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // установим ночную тему в соответствии с настройками системы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // temp new
        ipAddress = (EditText) findViewById(R.id.ipAddressEdit);
        cidr = (EditText) findViewById(R.id.cidr);
        netmask = (EditText) findViewById(R.id.netmaskEdit);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                // Здесь активность полностью прорисована и готова к использованию
                AuxiliaryVariables.setTextColorDefault(cidr.getCurrentTextColor());
            }
        });

        // задаем listener для поля ввода IP address
        ipAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable s) {
                String sIp = ipAddress.getText().toString();
                // проверяем что поле IP Address содержит корректное значение
                if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIp)) {
                    // ip address корректен, устанавливаем дефолтный цвет текста
                    ipAddress.setTextColor(AuxiliaryVariables.getTextColorDefault());
                } else {
                    ipAddress.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                }

            }
        });

        // задаем listener для поля ввода CIDR
        cidr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable s) {
                if (!isUpdating) {
                    // Блокируем обработку netmask listener
                    isUpdating = true;

                    String sCidr = cidr.getText().toString();
                    // проверяем что поле cidr содержит значение
                    if (isInteger(sCidr)) {
                        if (!"".contentEquals(s)) {
                            int iCidr = Integer.parseInt(sCidr);
                            if (iCidr >= 1 && iCidr <= 30) {
                                // cidr корректен, устанавливаем дефолтный цвет текста
                                cidr.setTextColor(AuxiliaryVariables.getTextColorDefault());
                                IpAddress.setCidr(iCidr);
                                netmask.setText(CalculationAddresses.calculationNetMask(iCidr));
                                // Поля сетевой маски и cidr заполнены корректно
                                CheckingCorrectnessNetmask.setNetmaskStatus(true);
                                CheckingCorrectnessNetmask.setCidrStatus(true);
                            } else {
                                cidr.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                                netmask.setText("");
                                // Поля сетевой маски и cidr заполнены не корректно
                                CheckingCorrectnessNetmask.setNetmaskStatus(false);
                                CheckingCorrectnessNetmask.setCidrStatus(false);
                            }
                        }
                    } else {
                        netmask.setText("");
                    }
                    // Снимаем блокировку netmask listener
                    isUpdating = false;
                }

            }
        });

        // задаем listener для поля ввода netmask
        netmask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isUpdating) {
                    // Блокируем обработку cidr listener
                    isUpdating = true;
                    cidr.setText("");

                    String input = editable.toString();
                    // Проверка на корректность сетевой маски IPv4
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(input)) {
                        // Маска корректна, устанавливаем дефолтный цвет текста
                        netmask.setTextColor(AuxiliaryVariables.getTextColorDefault());
                        // Проверим, введено полное значение маски, если да то заполним поле cidr
                        String netmask = CheckingCorrectnessNetmask.searchForCIDRByNetmask(input);
                        if (!netmask.isEmpty()) {
                            cidr.setText(netmask);
                            // Поля сетевой маски и cidr заполнены корректно
                            CheckingCorrectnessNetmask.setNetmaskStatus(true);
                            CheckingCorrectnessNetmask.setCidrStatus(true);
                        }
                    } else {
                        // Маска некорректна, устанавливаем красный цвет текста
                        netmask.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        // Поля сетевой маски и cidr заполнены не корректно
                        CheckingCorrectnessNetmask.setNetmaskStatus(false);
                        CheckingCorrectnessNetmask.setCidrStatus(false);
                    }
                    // Снимаем блокировку cidr listener
                    isUpdating = false; // Снимаем блокировку
                }

            }
        });
    }


    // Метод для проверки, является ли строка целым числом
    private boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input); // Попытка преобразования
            return true; // Успешное преобразование
        } catch (NumberFormatException e) {
            return false; // Преобразование не удалось
        }
    }


    // рисуем меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // обработка нажатий пунктов меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // переменная для определения выбранного пункта меню
        int id = item.getItemId();

        // переменная для определения темы системы day/night
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;

        switch (id) {
            case R.id.results:
                // делаем активным layout Results
                Intent intentResult = new Intent(this, ResultsActivity.class);
                startActivity(intentResult);
                return true;

            case R.id.privacy:
                // проверим что есть подключение к сети интернет
                if (isOnline()) {
                    // зададим url для перехода в зависимости от темы системы
                    switch (currentNightMode) {
                        case Configuration.UI_MODE_NIGHT_NO:
                            // Night mode is not active, we're in day time
                            url = "https://aleksandrbutakov.github.io/IPCalculatorForAndroid/" +
                                    "PolicyPrivacy/dayTheme/";
                            break;
                        case Configuration.UI_MODE_NIGHT_YES:
                            // Night mode is active, we're at night!
                            url = "https://aleksandrbutakov.github.io/IPCalculatorForAndroid/" +
                                    "PolicyPrivacy/nightTheme/";
                            break;
                        case Configuration.UI_MODE_NIGHT_UNDEFINED:
                            // We don't know what mode we're in, assume notnight
                            break;
                    }
                    // делаем активным activity_webview
                    intentPrivacy = new Intent(this, WebViewActivity.class);
                    startActivity(intentPrivacy);
                } else {
                    displayToast("Нет подключения к Интернету...");
                }
                return true;

            case R.id.about:
                // проверим что есть подключение к сети интернет
                if (isOnline()) {
                    // зададим url для перехода в зависимости от темы системы
                    switch (currentNightMode) {
                        case Configuration.UI_MODE_NIGHT_NO:
                            // Night mode is not active, we're in day time
                            url = "https://aleksandrbutakov.github.io/IPCalculatorForAndroid/" +
                                    "About/dayTheme/";
                            break;
                        case Configuration.UI_MODE_NIGHT_YES:
                            // Night mode is active, we're at night!
                            url = "https://aleksandrbutakov.github.io/IPCalculatorForAndroid/" +
                                    "About/nightTheme/";
                            break;
                        case Configuration.UI_MODE_NIGHT_UNDEFINED:
                            // We don't know what mode we're in, assume notnight
                            break;
                    }
                    // делаем активным activity_webView
                    intentPrivacy = new Intent(this, WebViewActivity.class);
                    startActivity(intentPrivacy);
                } else {
                    displayToast("Нет подключения к Интернету...");
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    // задаем listener для поля ввода IP адреса вкладки Tab1
//    public void listenerEditText1() {
//        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
//        EditText cIDR1 = (EditText) findViewById(R.id.cidr);
//        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
//
//        // Listener IP address tab1
//        ipAddressEdit1.addTextChangedListener(new TextWatcher() {
//
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (valueIPAddressFieldChangedByUser == true) {
//                    if (!ipAddressEdit1.getText().equals("")) {
//                        iPos = ipAddressEdit1.getSelectionStart() + 1;
//                    }
//                }
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (valueIPAddressFieldChangedByUser == true) {
//
//                    String sIP;
//                    sIP = String.valueOf(ipAddressEdit1.getText());
//
//                    // если введен некорректный IP адрес
//                    if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIP)) {
//                        // введен корректный IP адрес
//                        clearOutputFields1();
//                        sIPCorrectly = sIP;
//                        iPos = ipAddressEdit1.getSelectionStart();
//
//                        // проверим что все байты адреса заполнены
//                        if (!sIPCorrectlyB3.equals("") && !sIPCorrectlyB2.equals("") &&
//                                !sIPCorrectlyB1.equals("") && !sIPCorrectlyB0.equals("")) {
//                            tab1.ipAddressB3 = sIPCorrectlyB3;
//                            tab1.ipAddressB2 = sIPCorrectlyB2;
//                            tab1.ipAddressB1 = sIPCorrectlyB1;
//                            tab1.ipAddressB0 = sIPCorrectlyB0;
//                        } else {
//                            tab1.ipAddressB3 = "";
//                            tab1.ipAddressB2 = "";
//                            tab1.ipAddressB1 = "";
//                            tab1.ipAddressB0 = "";
//                        }
//
//                    } else {
//                        // введен неверный IP адрес
//                        valueIPAddressFieldChangedByUser = false;
//                        ipAddressEdit1.setText(sIPCorrectly);
//                        ipAddressEdit1.setSelection(iPos - 1);
//                    }
//                }
//                valueIPAddressFieldChangedByUser = true;
//            }
//        });
//
//        // задаем listener для поля ввода CIDR Tab1
//        cIDR1.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                cIDR1.setText("5555555");
//
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // temp origin
////                if (valueCIDRFieldChangedByUser == true) {
////                    if (!cIDR1.getText().equals("")) {
////                        iPos = cIDR1.getSelectionStart() + 1;
////                    }
////                }
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // temp origin
////                if (valueCIDRFieldChangedByUser == true) {
////                    String sCIDR;
////                    sCIDR = String.valueOf(cIDR1.getText());
////
////                    // если введен корректный или некорректный IP адрес
////                    if (CheckingCorrectnessCIDR.checkingCorrectnessCIDR(sCIDR)) {
////                        // введен корректный IP адрес
////                        clearOutputFields1();
////                        sCIDRCorrectly = sCIDR;
////                        iPos = cIDR1.getSelectionStart();
////
////                        // присвоим корректное значение маски переменной
////                        tab1.cidr = sCIDRCorrectly;
////                        // заполним поле netmask корректным значением
////                        valueNetmaskFieldChangedByUser = false;
////                        auxiliaryVariables = CheckingCorrectnessCIDR.
////                                searchForNetmaskByCIDR(sCIDRCorrectly);
////                        netmaskEdit1.setText(auxiliaryVariables);
////                        // присвоим переменной корректное значение для дальнейших вычислений
////                        sNetmaskCorrectly = auxiliaryVariables;
////                        // заполним значения байт маски для дальнейших вычислений
////                        CheckingCorrectnessNetmask.checkingCorrectnessNetmask(auxiliaryVariables);
////                        tab1.netmaskB3 = sNetmaskCorrectlyB3;
////                        tab1.netmaskB2 = sNetmaskCorrectlyB2;
////                        tab1.netmaskB1 = sNetmaskCorrectlyB1;
////                        tab1.netmaskB0 = sNetmaskCorrectlyB0;
////
////                    } else {
////                        // введен неверный IP адрес
////                        valueCIDRFieldChangedByUser = false;
////                        cIDR1.setText(sCIDRCorrectly);
////                        cIDR1.setSelection(iPos - 1);
////
////                        if (sCIDRCorrectly.equals("")) {
////                            // отсутствует корректное значение CIDR, очистим переменные
////                            tab1.cidr = "";
////                            tab1.netmaskB3 = "";
////                            tab1.netmaskB2 = "";
////                            tab1.netmaskB1 = "";
////                            tab1.netmaskB0 = "";
////                        }
////                    }
////                }
////                valueCIDRFieldChangedByUser = true;
//            }
//        });
//
//        // задаем listener для поля ввода netmaskEdit1 Tab1
//        netmaskEdit1.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (valueNetmaskFieldChangedByUser == true) {
//                    if (!netmaskEdit1.getText().equals("")) {
//                        iPos = netmaskEdit1.getSelectionStart() + 1;
//                    }
//                }
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (valueNetmaskFieldChangedByUser == true) {
//                    String sNetmask;
//                    sNetmask = String.valueOf(netmaskEdit1.getText());
//
//
//                    // если введен корректный или некорректный IP адрес
//                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(sNetmask)) {
//                        // введен корректный IP адрес
//                        clearOutputFields1();
//                        sNetmaskCorrectly = sNetmask;
//                        iPos = netmaskEdit1.getSelectionStart();
//
//                        // проверим что в поле netmask введено полное корректное значение по
//                        // которому можно определить маску CIDR и в таком случае заполним поле
//                        // CIDR соответствующим значением
//                        auxiliaryVariables = CheckingCorrectnessNetmask.
//                                searchForCIDRByNetmask(sNetmaskCorrectly);
//                        if (!auxiliaryVariables.equals("")) {
//                            valueCIDRFieldChangedByUser = false;
//                            cIDR1.setText(auxiliaryVariables);
//
//                            // заполним значения байт netmask and CIDR для дальнейших вычислений
//                            tab1.cidr = auxiliaryVariables;
//                            tab1.netmaskB3 = sNetmaskCorrectlyB3;
//                            tab1.netmaskB2 = sNetmaskCorrectlyB2;
//                            tab1.netmaskB1 = sNetmaskCorrectlyB1;
//                            tab1.netmaskB0 = sNetmaskCorrectlyB0;
//
//                        } else {
//                            valueCIDRFieldChangedByUser = false;
//                            cIDR1.setText("");
//
//                            // очистим значения байт netmask and CIDR, т.к. текущие
//                            // значения некорректны
//                            tab1.cidr = auxiliaryVariables;
//                            tab1.netmaskB3 = sNetmaskCorrectlyB3;
//                            tab1.netmaskB2 = sNetmaskCorrectlyB2;
//                            tab1.netmaskB1 = sNetmaskCorrectlyB1;
//                            tab1.netmaskB0 = sNetmaskCorrectlyB0;
//                        }
//
//                    } else {
//                        // введена неверная маска IP адрес
//                        valueNetmaskFieldChangedByUser = false;
//                        netmaskEdit1.setText(sNetmaskCorrectly);
//                        netmaskEdit1.setSelection(iPos - 1);
//                    }
//                }
//                valueNetmaskFieldChangedByUser = true;
//            }
//        });
//    }

    public void information() {
        Toast toast = Toast.makeText(MainActivity.this,
                "Invalid IP address entered...",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void displayToast(String text) {
        Toast toast = Toast.makeText(MainActivity.this,
                text,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    // нажатие кнопки CALC, проверяем корректность введенных данных и выводим результат
    public void onClickCalcButton(View v) {
//        // проверим что переменные для хранения байтов IP адреса не равны null
//        if (tab1.ipAddressB3.equals("") ||
//                tab1.ipAddressB2.equals("") ||
//                tab1.ipAddressB1.equals("") ||
//                tab1.ipAddressB0.equals("") ||
//                // проверим что переменная для хранения количества бит маски подсети не равна null
//                tab1.cidr.equals("") ||
//                // проверим что переменные для хранения байтов маски подсети не равны null
//                tab1.netmaskB3.equals("") ||
//                tab1.netmaskB2.equals("") ||
//                tab1.netmaskB1.equals("") ||
//                tab1.netmaskB0.equals("")) {
//            // введены некорректные данные
//            Toast.makeText(this, "Incorrect data entered...",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            // введены корректные данные. Рассчитываем все параметры и выводим на экран
//            EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
//            EditText cidr1 = (EditText) findViewById(R.id.cidr1);
//            EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
//
//            TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
//            TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
//            TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
//            TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
//            TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
//            TextView decNumberHostsText1 = (TextView) findViewById(R.id.decNumberHostsText1);
//
//            TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
//            TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
//            TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
//            TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
//            TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
//            TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);
//
//            // заполним массив boolean[] binIPAddressArray
//            CalculationAddresses.fillingTheBinIPAddressArray(tab1);
//            binIPAddressText1.setText(tab1.sIPAddressBin);
//
//            // заполним массив boolean[] binNetmaskArray
//            CalculationAddresses.fillingTheBinNetmaskArray(tab1);
//            binNetmaskText1.setText(tab1.sNetmaskBin);
//
//            // рассчитываем значение binNetwork[32], decNetwork
//            // binFirstAddress[32], binLastAddress[32], binBroadcast[32] и выводим на экран
//            CalculationAddresses.fillingTheBinNetworkArray(tab1);
//            decNetworkIPText1.setText(tab1.decNetwork);
//            binNetworkText1.setText(tab1.sNetworkBin);
//            decNetworkIPText1.setText(CalculationAddresses.binToDec(tab1.binNetworkArray));
//            binFirstAddressText1.setText(tab1.sFirstAddressBin);
//            decFirstAddressText1.setText(CalculationAddresses.binToDec(tab1.binFirstAddress));
//            binLastAddressText1.setText(tab1.sLastAddressBin);
//            decLastAddressText1.setText(CalculationAddresses.binToDec(tab1.binLastAddress));
//            binBroadcastText1.setText(tab1.sBroadcastBin);
//            decBroadcastText1.setText(CalculationAddresses.binToDec(tab1.binBroadcast));
//
//            // выводим параметр netmask на экран
//            decNetmaskText1.setText(netmaskEdit1.getText());
//
//            // рассчитываем параметр tab1.decNumberHosts и выводим на экран
//            CalculationAddresses.calculationNumberHosts(tab1);
//            decNumberHostsText1.setText(tab1.decNumberHosts);
//
//            // сворачиваем клавиатуру при нажатии на кнопку
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(ipAddressEdit1.getWindowToken(), 0);
//
//            // заполним переменные для дальнейшего использования в layout Results
//            tab1.decIPAddress = ipAddressEdit1.getText().toString();
//            tab1.decCIDR = cidr1.getText().toString();
//            tab1.decNetMask = netmaskEdit1.getText().toString();
//
//            tab1.decNetwork = decNetworkIPText1.getText().toString();
//            tab1.decFirstAddress = decFirstAddressText1.getText().toString();
//            tab1.decLastAddress = decLastAddressText1.getText().toString();
//            tab1.decBroadcast = decBroadcastText1.getText().toString();
//
//            // выводим результаты в поле Results
//            ResultsActivity resultsActivity = new ResultsActivity();
//            resultsActivity.outputResults(tab1);
//    }
    }

    // нажатие кнопки CLEAR, очищаем все поля ввода нулевой вкладки
    public void onClickClearButton(View v) {
        valueIPAddressFieldChangedByUser = false;
        valueCIDRFieldChangedByUser = false;
        valueNetmaskFieldChangedByUser = false;
    }

// очищаем все поля экрана Tab1 и устанавливаем им серый цвет текста
//    public void clearingFragment1DataTab1() {
//        EditText ipAddressEdit1 = (EditText) findViewById(R.id.ipAddressEdit1);
//        EditText cidr1 = (EditText) findViewById(R.id.cidr1);
//        EditText netmaskEdit1 = (EditText) findViewById(R.id.netmaskEdit1);
//
//        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
//        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
//        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
//        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
//        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
//        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);
//
//        TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
//        TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
//        TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
//        TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
//        TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
//        TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);
//
//        ipAddressEdit1.setText("");
//        cidr1.setText("");
//        netmaskEdit1.setText("");
//
//        // очищаем поля
//        decNetworkIPText1.setText("");
//        decBroadcastText1.setText("");
//        decNetmaskText1.setText("");
//        decFirstAddressText1.setText("");
//        decLastAddressText1.setText("");
//        decNumberText1.setText("");
//
//        binIPAddressText1.setText("");
//        binNetworkText1.setText("");
//        binBroadcastText1.setText("");
//        binNetmaskText1.setText("");
//        binFirstAddressText1.setText("");
//        binLastAddressText1.setText("");
//
//        valueIPAddressFieldChangedByUser = true;
//        valueCIDRFieldChangedByUser = true;
//        valueNetmaskFieldChangedByUser = true;
//    }

//    public void clearOutputFields1() {
//        TextView decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
//        TextView decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
//        TextView decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
//        TextView decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
//        TextView decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
//        TextView decNumberText1 = (TextView) findViewById(R.id.decNumberHostsText1);
//
//        TextView binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
//        TextView binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
//        TextView binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
//        TextView binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
//        TextView binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
//        TextView binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);
//
//        decNetworkIPText1.setText("");
//        decBroadcastText1.setText("");
//        decNetmaskText1.setText("");
//        decFirstAddressText1.setText("");
//        decLastAddressText1.setText("");
//        decNumberText1.setText("");
//        binIPAddressText1.setText("");
//        binNetworkText1.setText("");
//        binBroadcastText1.setText("");
//        binNetmaskText1.setText("");
//        binFirstAddressText1.setText("");
//        binLastAddressText1.setText("");
//    }

    // проверка наличия подключения к интернету
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }
}
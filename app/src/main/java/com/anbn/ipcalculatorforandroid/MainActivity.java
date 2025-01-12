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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public static String url = "";

    Intent intentPrivacy;

    // temp new
    private EditText ipAddress;
    private EditText cidr;
    private EditText netmask;

    private TextView decNetworkIPText;
    private TextView decBroadcastText;
    private TextView decNetmaskText;
    private TextView decFirstAddressText;
    private TextView decLastAddressText;
    private TextView decNumberText;

    private TextView binIPAddressText;
    private TextView binNetworkText;
    private TextView binBroadcastText;
    private TextView binNetmaskText;
    private TextView binFirstAddressText;
    private TextView binLastAddressText;

    private TextView decNetworkIPText1;
    private TextView decBroadcastText1;
    private TextView decNetmaskText1;
    private TextView decFirstAddressText1;
    private TextView decLastAddressText1;
    private TextView decNumberHostsText1;

    private TextView binIPAddressText1;
    private TextView binNetworkText1;
    private TextView binBroadcastText1;
    private TextView binNetmaskText1;
    private TextView binFirstAddressText1;
    private TextView binLastAddressText1;


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

        decNetworkIPText = (TextView) findViewById(R.id.decNetworkIPText1);
        decBroadcastText = (TextView) findViewById(R.id.decBroadcastText1);
        decNetmaskText = (TextView) findViewById(R.id.decNetmaskText1);
        decFirstAddressText = (TextView) findViewById(R.id.decFirstAddressText1);
        decLastAddressText = (TextView) findViewById(R.id.decLastAddressText1);
        decNumberText = (TextView) findViewById(R.id.decNumberHostsText1);

        binIPAddressText = (TextView) findViewById(R.id.binIPAddressText1);
        binNetworkText = (TextView) findViewById(R.id.binNetworkText1);
        binBroadcastText = (TextView) findViewById(R.id.binBroadcastText1);
        binNetmaskText = (TextView) findViewById(R.id.binNetmaskText1);
        binFirstAddressText = (TextView) findViewById(R.id.binFirstAddressText1);
        binLastAddressText = (TextView) findViewById(R.id.binLastAddressText1);

        decNetworkIPText1 = (TextView) findViewById(R.id.decNetworkIPText1);
        decBroadcastText1 = (TextView) findViewById(R.id.decBroadcastText1);
        decNetmaskText1 = (TextView) findViewById(R.id.decNetmaskText1);
        decFirstAddressText1 = (TextView) findViewById(R.id.decFirstAddressText1);
        decLastAddressText1 = (TextView) findViewById(R.id.decLastAddressText1);
        decNumberHostsText1 = (TextView) findViewById(R.id.decNumberHostsText1);

        binIPAddressText1 = (TextView) findViewById(R.id.binIPAddressText1);
        binNetworkText1 = (TextView) findViewById(R.id.binNetworkText1);
        binBroadcastText1 = (TextView) findViewById(R.id.binBroadcastText1);
        binNetmaskText1 = (TextView) findViewById(R.id.binNetmaskText1);
        binFirstAddressText1 = (TextView) findViewById(R.id.binFirstAddressText1);
        binLastAddressText1 = (TextView) findViewById(R.id.binLastAddressText1);

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
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                clearingOutputData();

                // temp origin
//                String sIp = ipAddress.getText().toString();
                String sIp = s.toString();

                // проверяем что поле IP Address содержит корректное значение
                if (CheckingCorrectnessIPAddress.checkingCorrectnessIPAddress(sIp)) {
                    // ip address корректен, устанавливаем дефолтный цвет текста
                    ipAddress.setTextColor(AuxiliaryVariables.getTextColorDefault());

                    // проверим что все байты адреса заполнены
                    if (!Data.getIpByte3().isEmpty() && !Data.getIpByte2().isEmpty() &&
                            !Data.getIpByte1().isEmpty() && !Data.getIpByte0().isEmpty()) {

                    } else {
                        cleanIpAddressData();
                    }

                } else {
                    ipAddress.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                    // очищаем поля вывода результатов
                    cleanIpAddressData();
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });

        // задаем listener для поля ввода CIDR
        cidr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!isUpdating) {
                    // Блокируем обработку netmask listener
                    isUpdating = true;

                    cleanCidrData();
                    cleanNetmaskData();
                    clearingOutputData();

                    // temp origin
//                    String sCidr = cidr.getText().toString();
                    String sCidr = s.toString();

                    // проверяем что поле cidr содержит значение
                    if (isInteger(sCidr)) {
                        if (!"".contentEquals(s)) {
                            int iCidr = Integer.parseInt(sCidr);
                            if (iCidr >= 1 && iCidr <= 30) {
                                // cidr корректен, устанавливаем дефолтный цвет текста
                                cidr.setTextColor(AuxiliaryVariables.getTextColorDefault());
//                                IpAddress.setStrCidr(iCidr);
                                netmask.setTextColor(AuxiliaryVariables.getTextColorDefault());
                                netmask.setText(CalculationAddresses.calculationNetMask(iCidr));
                                // присвоим переменной корректное значение для дальнейших вычислений
                                Data.setFullMask(CheckingCorrectnessCIDR.searchForNetmaskByCIDR(sCidr));
                                Data.setStrCidr(sCidr);
                                // заполним значения байт маски для дальнейших вычислений
                                CheckingCorrectnessNetmask.checkingCorrectnessNetmask(Data.getFullMask());
                            } else {
                                cidr.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                                netmask.setText("");
                                cleanCidrData();
                                cleanNetmaskData();
                            }
                        }
                    } else {
                        netmask.setText("");
                        cleanCidrData();
                        cleanNetmaskData();
                    }
                    // Снимаем блокировку netmask listener
                    isUpdating = false;
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });

        // задаем listener для поля ввода netmask
        netmask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int z = 0;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!isUpdating) {
                    // Блокируем обработку cidr listener
                    isUpdating = true;

                    cidr.setText("");
                    cleanNetmaskData();
                    cleanCidrData();

                    String input = s.toString();
                    // Проверка на корректность сетевой маски IPv4
                    if (CheckingCorrectnessNetmask.checkingCorrectnessNetmask(input)) {
                        // Маска корректна, устанавливаем дефолтный цвет текста
                        netmask.setTextColor(AuxiliaryVariables.getTextColorDefault());
                        // Проверим, введено полное значение маски, если да то заполним поле cidr
                        String netmask = CheckingCorrectnessNetmask.searchForCIDRByNetmask(input);
                        if (!netmask.isEmpty()) {
                            cidr.setTextColor(AuxiliaryVariables.getTextColorDefault());
                            cidr.setText(netmask);
                            // присвоим переменной корректное значение для дальнейших вычислений
                            Data.setFullMask(CheckingCorrectnessCIDR.searchForNetmaskByCIDR(input));
                            // заполним значения байт маски для дальнейших вычислений
                            CheckingCorrectnessNetmask.checkingCorrectnessNetmask(Data.getFullMask());
//                            data.cidr = netmask;
                            Data.setStrCidr(netmask);
                        }
                    } else {
                        // Маска некорректна, устанавливаем красный цвет текста
                        netmask.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        cleanCidrData();
                        cleanNetmaskData();
                        clearingOutputData();
                    }
                    // Снимаем блокировку cidr listener
                    isUpdating = false; // Снимаем блокировку
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
        // проверим что переменные для хранения байтов IP адреса не равны null
        if (Data.getIpByte3().isEmpty() ||
                Data.getIpByte2().isEmpty() ||
                Data.getIpByte1().isEmpty() ||
                Data.getIpByte0().isEmpty() ||
                // проверим что переменная для хранения количества бит маски подсети не равна null
                Data.getStrCidr().isEmpty() ||
                // проверим что переменные для хранения байтов маски подсети не равны null
                Data.getMaskByte3().isEmpty() ||
                Data.getMaskByte2().isEmpty() ||
                Data.getMaskByte1().isEmpty() ||
                Data.getMaskByte0().isEmpty()) {
            // введены некорректные данные
            Toast.makeText(this, "Incorrect data entered...",
                    Toast.LENGTH_SHORT).show();

            clearingOutputData();

        } else {
            // введены корректные данные. Рассчитываем все параметры и выводим на экран

            // заполним массив boolean[] binIPAddressArray
            CalculationAddresses.fillingTheBinIPAddressArray();
            binIPAddressText1.setText(Data.getStrIpAddressBin()); // data.sIPAddressBin);

            // заполним массив boolean[] binNetmaskArray
            CalculationAddresses.fillingTheBinNetmaskArray();
            binNetmaskText1.setText(Data.getStrNetmaskBin()); // data.sNetmaskBin);

            // рассчитываем значение binNetwork[32], decNetwork
            // binFirstAddress[32], binLastAddress[32], binBroadcast[32] и выводим на экран
            CalculationAddresses.fillingTheBinNetworkArray();
            decNetworkIPText1.setText(Data.getDecNetwork()); // data.decNetwork);
            binNetworkText1.setText(Data.getStrNetworkBin()); // data.sNetworkBin);
            decNetworkIPText1.setText(CalculationAddresses.binToDec(Data.getBinNetworkArray())); // data.binNetworkArray));
            binFirstAddressText1.setText(Data.getStrFirstAddressBin()); // data.sFirstAddressBin);
            decFirstAddressText1.setText(CalculationAddresses.binToDec(Data.getBinFirstAddress())); // data.binFirstAddress));
            binLastAddressText1.setText(Data.getStrLastAddressBin()); // data.sLastAddressBin);
            decLastAddressText1.setText(CalculationAddresses.binToDec(Data.getBinLastAddress())); // data.binLastAddress));
            binBroadcastText1.setText(Data.getStrBroadcastBin()); // data.sBroadcastBin);
            decBroadcastText1.setText(CalculationAddresses.binToDec(Data.getBinBroadcast())); // data.binBroadcast));

            // выводим параметр netmask на экран
            decNetmaskText1.setText(netmask.getText());

            // рассчитываем параметр tab1.decNumberHosts и выводим на экран
            CalculationAddresses.calculationNumberHosts();
            decNumberHostsText1.setText(Data.getDecNumberHosts()); // data.decNumberHosts);

            // сворачиваем клавиатуру при нажатии на кнопку
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ipAddress.getWindowToken(), 0);

            // заполним переменные для дальнейшего использования в layout Results
//            data.decIPAddress = ipAddress.getText().toString();
            Data.setDecIPAddress(ipAddress.getText().toString());
//            data.decCIDR = cidr.getText().toString();
            Data.setDecCIDR(cidr.getText().toString());
//            data.decNetMask = netmask.getText().toString();
            Data.setDecNetMask(netmask.getText().toString());

//            data.decNetwork = decNetworkIPText1.getText().toString();
            Data.setDecNetwork(decNetworkIPText1.getText().toString());
//            data.decFirstAddress = decFirstAddressText1.getText().toString();
            Data.setDecFirstAddress(decFirstAddressText1.getText().toString());
//            data.decLastAddress = decLastAddressText1.getText().toString();
            Data.setDecLastAddress(decLastAddressText1.getText().toString());
//            data.decBroadcast = decBroadcastText1.getText().toString();
            Data.setDecBroadcast(decBroadcastText1.getText().toString());

            // выводим результаты в поле Results
            ResultsActivity resultsActivity = new ResultsActivity();
            resultsActivity.outputResults();
        }
    }

    // нажатие кнопки CLEAR, очищаем все поля ввода нулевой вкладки
    public void onClickClearButton(View v) {
        clearingEditTextFields();
        clearingOutputData();
    }

    public void clearingEditTextFields() {
        // очищаем поля
        ipAddress.setText("");
        cidr.setText("");
        netmask.setText("");
    }

    // очищаем все поля экрана Tab1 и устанавливаем им серый цвет текста
    public void clearingOutputData() {
        // очищаем поля
        decNetworkIPText.setText("");
        decBroadcastText.setText("");
        decNetmaskText.setText("");
        decFirstAddressText.setText("");
        decLastAddressText.setText("");
        decNumberText.setText("");

        binIPAddressText.setText("");
        binNetworkText.setText("");
        binBroadcastText.setText("");
        binNetmaskText.setText("");
        binFirstAddressText.setText("");
        binLastAddressText.setText("");
    }

    public void cleanIpAddressData() {
        Data.setIpByte3("");
        Data.setIpByte2("");
        Data.setIpByte1("");
        Data.setIpByte0("");
    }

    public void cleanCidrData() {
        Data.setStrCidr("");
    }

    public void cleanNetmaskData() {
        Data.setMaskByte3("");
        Data.setMaskByte2("");
        Data.setMaskByte1("");
        Data.setMaskByte0("");
    }

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
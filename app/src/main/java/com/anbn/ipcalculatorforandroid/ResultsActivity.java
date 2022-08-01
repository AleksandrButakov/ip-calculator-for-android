package com.anbn.ipcalculatorforandroid;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    public static String s = "Attention!\nThe results of calculations are not saved after " +
            "the application is closed.\n\n\n";
    public static int calculationNumber = 1;

    // нарисуем экран
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView textViewResults = (TextView) findViewById(R.id.textViewResults);
        textViewResults.setMovementMethod(ScrollingMovementMethod.getInstance());
        textViewResults.setText(s);
    }

    // метод нажатия на кнопку
    public void onClickButtonBack(View v) {
        // возврат на предыдущий activity
        onBackPressed();
    }


    // выводим логи на экран
    public void outputResults(CalculationAddresses tab) {

        s += "CALCULATION NUMBER #" + calculationNumber +"\n";
        calculationNumber++;
        s += "IP address: " + tab.decIPAddress + "\n";
        s += "CIDR:       " + tab.decCIDR  + "\n";
        s += "Netmask:    " + tab.decNetMask + "\n\n";
        s += "Decimal data format:\n";
        s += "Network:       " + tab.decNetwork + "\n";
        s += "First Address: " + tab.decFirstAddress + "\n";
        s += "Last Address:  " + tab.decLastAddress + "\n";
        s += "Broadcast:     " + tab.decBroadcast + "\n";
        s += "Number Hosts:  " + tab.decNumberHosts + "\n\n";
        s += "Binary data format:\n";
        s += "IP address:\n" + tab.sIPAddressBin + "\n";
        s += "Network:\n" + tab.sNetworkBin + "\n";
        s += "Netmask:\n" + tab.sNetmaskBin + "\n";
        s += "First address:\n" + tab.sFirstAddressBin + "\n";
        s += "Last address:\n" + tab.sLastAddressBin + "\n";
        s += "Broadcast address:\n" + tab.sBroadcastBin + "\n\n\n";

        /*
        String sIPAddressBin;
        String sNetworkBin;
        String sNetmaskBin;
        String sFirstAddressBin;
        String sLastAddressBin;
        String sBroadcastBin;
*/

        /*
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
         */


    }

}
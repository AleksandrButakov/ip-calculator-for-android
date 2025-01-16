package com.anbn.ipcalculatorforandroid;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    public static String s = "Attention!\n" +
            "The results of calculations are not saved after the application is closed.\n" +
            "-----------------------------------\n\n";
    public static int calculationNumber = 1;

    // нарисуем экран
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // для стрелки назад в ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add scrolling to editText
        TextView textViewResults = (TextView) findViewById(R.id.textViewResults);
        textViewResults.setMovementMethod(ScrollingMovementMethod.getInstance());
        textViewResults.setText(s);

    }

    // для стрелки назад в ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // для стрелки назад в ActionBar
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    // выводим логи на экран
    public void outputResults() {
        s = s + "CALCULATION NUMBER #" + calculationNumber + "\n";
        calculationNumber++;
        s += "IP address: " + Data.getDecIPAddress() + "\n"; // tab.decIPAddress + "\n";
        s += "CIDR:       " + Data.getDecCIDR() + "\n"; // tab.decCIDR + "\n";
        s += "Netmask:    " + Data.getDecNetMask() + "\n"; // tab.decNetMask + "\n\n";
        s += "Decimal data format:\n";
        s += "Network:       " + Data.getDecNetwork() + "\n"; // tab.decNetwork + "\n";
        s += "First Address: " + Data.getDecFirstAddress() + "\n"; // tab.decFirstAddress + "\n";
        s += "Last Address:  " + Data.getDecLastAddress() + "\n"; // tab.decLastAddress + "\n";
        s += "Broadcast:     " + Data.getDecBroadcast() + "\n"; // tab.decBroadcast + "\n";
        s += "Number Hosts:  " + Data.getDecNumberHosts() + "\n\n"; // tab.decNumberHosts + "\n\n";
        s += "Binary data format:\n";
        s += "IP address:\n" + Data.getStrIpAddressBin() + "\n"; // tab.sIPAddressBin + "\n";
        s += "Network:\n" + Data.getStrNetworkBin() + "\n"; // tab.sNetworkBin + "\n";
        s += "Netmask:\n" + Data.getStrNetmaskBin() + "\n"; // tab.sNetmaskBin + "\n";
        s += "First address:\n" + Data.getStrFirstAddressBin() + "\n"; // tab.sFirstAddressBin + "\n";
        s += "Last address:\n" + Data.getStrLastAddressBin() + "\n"; // tab.sLastAddressBin + "\n";
        s += "Broadcast address:\n" + Data.getStrBroadcastBin() + "\n"; // tab.sBroadcastBin + "\n";
        s += "-----------------------------------";
        s += "\n\n";
    }

    public void onClickCleanButtonActivityResults(View v) {
        s = "Attention!\n" +
                "The results of calculations are not saved after the application is closed.\n" +
                "-----------------------------------\n\n";
        // add scrolling to editText
        TextView textViewResults = (TextView) findViewById(R.id.textViewResults);
        //textViewResults.setMovementMethod(ScrollingMovementMethod.getInstance());
        textViewResults.setText(s);
    }
}
package com.anbn.ipcalculatorforandroid;

import android.content.res.Configuration;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
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
    public void outputResults(CalculationAddresses tab) {
//        s += "---------------------------" + "\n";
        s += "CALCULATION NUMBER #" + calculationNumber + "\n";
        calculationNumber++;
        s += "IP address: " + tab.decIPAddress + "\n";
        s += "CIDR:       " + tab.decCIDR + "\n";
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
//        s += "Broadcast address:\n" + tab.sBroadcastBin + "\n\n\n";
        s += "Broadcast address:\n" + tab.sBroadcastBin + "\n";
        s += "-----------------------------------";
        s += "\n\n";

    }

    public void onClickCleanButtonActivityResults(View v) {
        s = "";
        // add scrolling to editText
        TextView textViewResults = (TextView) findViewById(R.id.textViewResults);
        textViewResults.setMovementMethod(ScrollingMovementMethod.getInstance());
        textViewResults.setText(s);
    }

}
package com.example.wifibluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch s, s2;
    BluetoothAdapter ba;
    WifiManager wm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ba = BluetoothAdapter.getDefaultAdapter();

        s = findViewById(R.id.switch1);
        s2 = findViewById(R.id.switch2);

        s.setOnCheckedChangeListener(this);
        s2.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (compoundButton == s) {
            if (b) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i, 0);

            } else {
                ba.disable();
            }
        } else if (compoundButton == s2) {
            if (b) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    Intent intent = new Intent(Settings.Panel.ACTION_WIFI);
                    startActivityForResult(intent, 1);

                } else {
                    wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                    wm.setWifiEnabled(true);
                }
            } else {
                wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                wm.setWifiEnabled(false);
            }

        }
    }
}
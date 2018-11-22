package com.example.anna.alzheimerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anna.alzheimerapp.reminder.AddReminder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonReminder, buttonMyFamily, buttonMyLocalisation, buttonSOS;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        buttonMyFamily = (Button) findViewById(R.id.buttonMyFamily);
        buttonMyFamily.setOnClickListener(this);
        buttonMyLocalisation = (Button) findViewById(R.id.buttonMyLocalisation);
        buttonMyLocalisation.setOnClickListener(this);
        buttonReminder = (Button) findViewById(R.id.buttonReminder);
        buttonReminder.setOnClickListener(this);
        buttonSOS = (Button) findViewById(R.id.buttonSOS);
        buttonSOS.setOnClickListener(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        };
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                Toast.makeText(MainActivity.this, "Mam dostep do pozwolenia", Toast.LENGTH_LONG).show();

                return;
            }
        }
        else {
            configure();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length> 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                    configure();
                return;
        }
    }

    private void configure() {
        buttonMyLocalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Nie mam dostepu", Toast.LENGTH_LONG).show();

                locationManager.requestLocationUpdates("gps", 1000, 0, locationListener);


            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonMyFamily:
                Intent intent = new Intent(MainActivity.this, FamilyOption.class);
                startActivity(intent);
                break;
            case R.id.buttonMyLocalisation:
                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case R.id.buttonReminder:
                Intent intent3 = new Intent(MainActivity.this, com.example.anna.alzheimerapp.reminder.Reminder.class);
                startActivity(intent3);
                break;
            case R.id.buttonSOS:
                Intent intent4 = new Intent(this, SendSOS.class);
                startActivity(intent4);
                break;
        }
    }
}

package com.example.anna.alzheimerapp.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.anna.alzheimerapp.R;

public class AddReminder extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    AlarmManager alarmManager;
    TimePicker timePicker;
    private TextView textViewDate, textViewTime, textViewSetDate, textViewSetTime;
    private EditText editTextNotificationContent;
    Spinner spinnerHour, spinnerMinute;
    private Button addAlarm;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    String hours, minutes, notificationContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        init();
    }
    public void init(){
        spinnerHour=(Spinner)findViewById(R.id.spinnerHour);
        spinnerHour.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.hours_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerHour.setAdapter(adapter);


        spinnerMinute=(Spinner)findViewById(R.id.spinnerMinute);
        spinnerMinute.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.minutes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMinute.setAdapter(adapter2);

        editTextNotificationContent = (EditText)findViewById(R.id.editTextNotificationContent);

//        editTexthour =(EditText)findViewById(R.id.editTexthour);
//        editTextMinute =(EditText)findViewById(R.id.editTextMinute);
        addAlarm = (Button)findViewById(R.id.addAlarm);
        addAlarm.setOnClickListener(this);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

//        textViewDate = (TextView)findViewById(R.id.textViewDate);
//        textViewDate.setOnClickListener(this);

//        textViewSetDate = (TextView)findViewById(R.id.textViewSetHour);
//        textViewSetTime = (TextView)findViewById(R.id.textViewSetTime);

//        textViewTime = (TextView)findViewById(R.id.textViewTime);
//        textViewTime.setOnClickListener(this);

//        Intent incomingDateIntent = getIntent();
//        String date = incomingDateIntent.getStringExtra("date");
//        textViewSetDate.setText(date);
//
//        Intent incomingTimeIntent = getIntent();
//        String time = incomingTimeIntent.getStringExtra("date");
//        textViewSetDate.setText(date);

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.addAlarm:
                Alarm alarm = new Alarm();
                alarm.setAlarm(this, hours,  minutes);
                Toast.makeText(this, hours + ": " + minutes, Toast.LENGTH_SHORT).show();
//                notificationContent= editTextNotificationContent.getText().toString();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.spinnerHour:
                hours = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinnerMinute:
                minutes = parent.getItemAtPosition(position).toString();
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

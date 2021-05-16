package sg.edu.rp.c346.id20037834.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etPhoneNumber;
    EditText etGroupSize;
    DatePicker datePicker;
    TimePicker timePicker;
    RadioGroup tablePreference;
    RadioButton rbSmoking;
    RadioButton rbNonSmoking;
    Button btnReserve;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etMobileNumber);
        etGroupSize = findViewById(R.id.etGroupSize);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        tablePreference = findViewById(R.id.rgTablePreference);
        rbSmoking = findViewById(R.id.rbSmoking);
        rbNonSmoking = findViewById(R.id.rbNonSmoking);
        btnReserve = findViewById(R.id.btnReserve);
        btnReset = findViewById(R.id.btnReset);


        Calendar thisCalendar = Calendar.getInstance();
        int currentYear = thisCalendar.get(thisCalendar.YEAR);
        int currentMonth = thisCalendar.get(thisCalendar.MONTH);
        int currentDay = thisCalendar.get(thisCalendar.DAY_OF_MONTH);

        datePicker.updateDate(currentYear,currentMonth,currentDay + 1);
        timePicker.setCurrentHour(19);
        timePicker.setCurrentMinute(30);
        datePicker.setMinDate(System.currentTimeMillis()+86400000);



        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                String strPhoneNumber = etPhoneNumber.getText().toString();
                String strGroupSize = etGroupSize.getText().toString();


                if(strName.isEmpty() || strPhoneNumber.isEmpty() || strGroupSize.isEmpty() ||
                tablePreference.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this,"Please Enter the empty spaces",Toast.LENGTH_SHORT).show();
                }
                else if(rbSmoking.isChecked()){
                    Toast.makeText(MainActivity.this,"Reservations with smoking table has been made",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Reservations with Non-Smoking table has been made",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.updateDate(2020,5,1);
                timePicker.setCurrentHour(19);
                timePicker.setCurrentMinute(30);

                etName.setText("");
                etGroupSize.setText("");
                etPhoneNumber.setText("");
                tablePreference.clearCheck();
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8 || hourOfDay > 20 ){
                    timePicker.setCurrentHour(20);
                    timePicker.setCurrentMinute(0);
                }
            }
        });
    }
}
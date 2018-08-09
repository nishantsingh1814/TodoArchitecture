package com.eventx.todoarchitecture;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.eventx.todoarchitecture.viewmodels.AddTodoViewModel;
import com.eventx.todoarchitecture.data.TodoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText description;
    private Button dateButton;
    private Button timeButton;
    private CheckBox repeat;
    private Spinner loop;
    Calendar mCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    TimePickerDialog.OnTimeSetListener time;
    FloatingActionButton addFine;

    private AddTodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_keep);

        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.description);
        dateButton = findViewById(R.id.date);
        timeButton = findViewById(R.id.time);
        repeat = findViewById(R.id.repeat);
        loop = findViewById(R.id.loop);
        dateButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this).get(AddTodoViewModel.class);

        ArrayList<String> list = new ArrayList<>();
        list.add("Every Minute");
        list.add("Every Hour");
        list.add("Every Day");
        list.add("Every Week");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loop.setAdapter(dataAdapter);


        addFine = findViewById(R.id.addFine);
        addFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateButton.getText().toString().equals("Select Date")) {
                    if (timeButton.getText().toString().equals("Select Time")) {
                        Toast.makeText(AddTodoActivity.this, "select date and time", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(AddTodoActivity.this, "select date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (timeButton.getText().toString().equals("Select Time")) {
                    Toast.makeText(AddTodoActivity.this, "select time", Toast.LENGTH_SHORT).show();
                    return;
                }
                long epoch = mCalendar.getTimeInMillis();
                if (epoch < Calendar.getInstance().getTimeInMillis()) {
                    Toast.makeText(AddTodoActivity.this, "select future time", Toast.LENGTH_LONG).show();
                    return;
                }
                TodoModel todoModel = new TodoModel(title.getText().toString().trim()
                        , description.getText().toString().trim()
                        , epoch
                        , repeat.isChecked() ? loop.getSelectedItem().toString() : null
                );
                viewModel.addNote(todoModel);



                finish();
            }
        });
        repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (repeat.isChecked()) {
                    loop.setVisibility(View.VISIBLE);
                } else if (!repeat.isChecked()) {
                    loop.setVisibility(View.INVISIBLE);
                }
            }
        });


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateButton.setText(sdf.format(mCalendar.getTime()));
            }
        };
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);

                String timeSet = "";
                if (hourOfDay > 12) {
                    hourOfDay -= 12;
                    timeSet = "PM";
                } else if (hourOfDay == 0) {
                    hourOfDay += 12;
                    timeSet = "AM";
                } else if (hourOfDay == 12)
                    timeSet = "PM";
                else
                    timeSet = "AM";

                String hour = "";
                if (hourOfDay < 10) {
                    hour = "0" + hourOfDay;
                } else {
                    hour = "" + hourOfDay;
                }
                String minutes = "";
                if (minute < 10)
                    minutes = "0" + minute;
                else {
                    minutes = "" + minute;
                }
                timeButton.setText(hour + ":" + minutes + " " + timeSet);
            }
        };
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.date) {
            new DatePickerDialog(AddTodoActivity.this, date, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
        } else if (id == R.id.time) {
            new TimePickerDialog(AddTodoActivity.this, time, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false).show();
        }

    }
}

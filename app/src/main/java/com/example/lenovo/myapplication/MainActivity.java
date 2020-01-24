package com.example.lenovo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RatingBar.OnRatingBarChangeListener, AdapterView.OnItemSelectedListener {
EditText edt_name,edt_dob;
TextView dob,rname,rdob,rgen,rdep,rrating;

Button btn;
private RadioGroup radioGroup;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        edt_name=findViewById(R.id.edt_name);
        rname=findViewById(R.id.rname);
        rdob=findViewById(R.id.rdob);
        rgen=findViewById(R.id.rgen);
        rdep=findViewById(R.id.rdep);
        rrating=findViewById(R.id.rrating);
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");
        categories.add("Trans");
        btn=findViewById(R.id.btn_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rname.setText(edt_name.getText());
                rdob.setText(edt_dob.getText());

            }
        });
        ArrayAdapter<String> dataadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categories);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataadapter);
        edt_dob=findViewById(R.id.edt_dob);
        final RatingBar ratingBar=(RatingBar)findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener((RatingBar.OnRatingBarChangeListener) this);
        radioGroup=(RadioGroup)findViewById(R.id.radio);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                    rdep.setText(rb.getText());
                }
            }
        });
        dob=findViewById(R.id.txt_dob);
        dob.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v){
        if (v==dob){
            final Calendar c =Calendar.getInstance();
            mYear=c.get(Calendar.YEAR);
            mMonth=c.get(Calendar.MONTH);
            mDay=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edt_dob.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item= parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),item+"",Toast.LENGTH_SHORT).show();
        rgen.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(getApplicationContext(),rating+"",Toast.LENGTH_SHORT).show();
        rrating.setText(rating+"");
    }
}

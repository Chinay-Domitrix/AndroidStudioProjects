package com.example.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.spinner.R.layout.activity_main;
import static com.example.spinner.R.layout.support_simple_spinner_dropdown_item;
import static java.lang.String.*;
import static java.util.Arrays.asList;
import static java.util.Collections.*;

public class MainActivity extends AppCompatActivity {
	Spinner spinner;
	TextView textView;
	ArrayList<String> stringArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		spinner = findViewById(R.id.spinner);
		textView = findViewById(R.id.textView);
		stringArrayList = new ArrayList<>();
		addAll(stringArrayList, "", "Tim", "Jim", "Kim", "Pim");
		spinner.setAdapter(new ArrayAdapter<>(this, support_simple_spinner_dropdown_item, stringArrayList));
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				textView.setText(format("Selection: %s", stringArrayList.get(position)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
}
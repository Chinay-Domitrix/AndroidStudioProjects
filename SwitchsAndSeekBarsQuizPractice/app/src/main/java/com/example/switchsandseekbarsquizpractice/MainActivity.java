package com.example.switchsandseekbarsquizpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import static com.example.switchsandseekbarsquizpractice.R.id.*;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SwitchMaterial aSwitch = findViewById(mySwitch);
		EditText editText = findViewById(colorEditText);
		TextView textView = findViewById(textView1);
	}
}
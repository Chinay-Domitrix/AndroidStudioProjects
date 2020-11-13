package com.example.switchesandseekbarsquiz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import static com.example.switchesandseekbarsquiz.R.id.password;
import static com.example.switchesandseekbarsquiz.R.id.passwordConfirm;
import static com.example.switchesandseekbarsquiz.R.id.switch1;
import static com.example.switchesandseekbarsquiz.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
	EditText[] editTexts;
	SwitchMaterial switchMaterial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		editTexts = new EditText[]{findViewById(password), findViewById(passwordConfirm)};
		switchMaterial = findViewById(switch1);
		editTexts[0].addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				changeSwitch();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		editTexts[1].addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				changeSwitch();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	private void changeSwitch(){
		switchMaterial.setChecked(!editTexts[0].getText().toString().equals(editTexts[1].getText().toString()));
	}
}
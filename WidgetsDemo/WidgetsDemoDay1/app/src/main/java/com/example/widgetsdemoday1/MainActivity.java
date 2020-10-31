package com.example.widgetsdemoday1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.widgetsdemoday1.R.id;
import static com.example.widgetsdemoday1.R.id.id_text_editText;
import static com.example.widgetsdemoday1.R.id.id_text_editThing;
import static com.example.widgetsdemoday1.R.id.seekBar1;
import static com.example.widgetsdemoday1.R.id.text1;
import static com.example.widgetsdemoday1.R.id.textView2;
import static com.example.widgetsdemoday1.R.layout.activity_main;
import static com.example.widgetsdemoday1.R.string;

public class MainActivity extends AppCompatActivity {

	TextView switchText;
	Switch mySwitch;

	SeekBar mySeekBar;
	TextView seekText;

	TextView editTextText;
	EditText myText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);

		switchText = findViewById(text1);
		mySwitch = findViewById(id.mySwitch);

		mySeekBar = findViewById(seekBar1);
		seekText = findViewById(textView2);

		editTextText = findViewById(id_text_editText);
		myText = findViewById(id_text_editThing);

		mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
			mySwitch.setShowText(true);
			switchText.setText(isChecked ? string.on : string.off);
		});

		mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				String x = getString(string.progressBar) + progress;
				seekText.setText(x);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		myText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editTextText.setText("IN FLUX");
			}

			@Override
			public void afterTextChanged(Editable s) {
				editTextText.setText(myText.getText());
			}
		});
	}
}
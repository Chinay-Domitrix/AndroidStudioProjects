package com.example.layoutdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static com.example.layoutdemo.R.id.button_port;
import static com.example.layoutdemo.R.id.hello;
import static com.example.layoutdemo.R.id.left_button_land;
import static com.example.layoutdemo.R.id.right_button_land;
import static com.example.layoutdemo.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

	TextView textView;
	Button centerButtonPort;
	Button leftButtonLand, rightButtonLand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);

		textView = findViewById(hello);
		centerButtonPort = findViewById(button_port);
		leftButtonLand = findViewById(left_button_land);
		rightButtonLand = findViewById(right_button_land);
		textView.setText("HI");
		if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT)
			centerButtonPort.setText("My Button");
		else if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
			leftButtonLand.setText("Left");
			rightButtonLand.setText("Right");
		}
	}
}
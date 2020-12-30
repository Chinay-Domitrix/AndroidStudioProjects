package com.example.lifecycledemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.lifecycledemo.R.id.button1;
import static com.example.lifecycledemo.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
	Button button;
	int anInt = 0;
	int anotherInt = anInt;
	public static final String ROTATE_INT_VALUE = "KEY001";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		button = findViewById(button1);
		Log.d("CREATE", "onCreate");
		if (savedInstanceState != null) {
			anInt = savedInstanceState.getInt("key");
			String x = "Value:" + anInt;
			button.setText(x);
		}
		button.setOnClickListener(v -> {
			anInt++;
			String x = "The value is " + anInt;
			button.setText(x);
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("START", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("RESUME", "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("PAUSE", "onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("DESTROY", "onDestroy");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("STOP", "onStop");
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
		outState.putInt(ROTATE_INT_VALUE, anInt);
	}
	private static double toInches(Number number){
		return number.doubleValue()/2.54;
	}
//	private static int toCentimeters(double )
}